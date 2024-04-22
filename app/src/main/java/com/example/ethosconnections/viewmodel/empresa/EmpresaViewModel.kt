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
import retrofit2.HttpException

class EmpresaViewModel (private val context: Context, private val repository: EmpresaRepository) : ViewModel(){

    private val empresaDataStore: EmpresaDataStore = EmpresaDataStore(context)

    val empresa = MutableLiveData<Empresa>()
    val empresas = MutableLiveData(SnapshotStateList<Empresa>())
    val errorMessage = MutableLiveData("")

    fun loginEmpresa(email: String, senha: String, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.loginEmpresa(email, senha)
                if (response.isSuccessful) {

                    empresa.postValue(response.body())
                    errorMessage.postValue("")

                    response.body()?.let { empresaDataStore.saveEmpresa(it) }
                    callback(true)
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                    callback(false)
                }
            } catch (e: HttpException) {
                Log.e("ViewModel", "Erro na HTTP: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao logar")
                callback(false)
            } catch (e: Exception) {
                Log.e("ViewModel", "Excecao: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao salvar o filme")
                callback(false)
            }
        }
    }
}

