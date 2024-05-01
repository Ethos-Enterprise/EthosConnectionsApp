package com.example.ethosconnections.viewmodel.empresa

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class EmpresaViewModel(private val context: Context, private val repository: EmpresaRepository) : ViewModel() {

     val empresaDataStore: EmpresaDataStore = EmpresaDataStore(context)

    val empresa = MutableLiveData<Empresa>()
    val errorMessage = MutableLiveData<String>()

    fun loginEmpresa(email: String, senha: String, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.loginEmpresa(email, senha)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        empresa.value = response.body()
                        errorMessage.value = ""
                        response.body()?.let { empresaDataStore.saveEmpresa(it) }
                        callback(true)
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: "Erro desconhecido"
                        callback(false)
                    }
                }
            } catch (e: HttpException) {
                Log.e("ViewModel", "Erro na HTTP: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro ao logar"
                    callback(false)
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Excecao: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro ao logar"
                    callback(false)
                }
            }
        }
    }
}
