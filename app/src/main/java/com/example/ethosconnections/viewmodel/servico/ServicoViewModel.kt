package com.example.ethosconnections.viewmodel.servico

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.repositories.ServicoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ServicoViewModel constructor(private val repository: ServicoRepository): ViewModel(){

    val servicos = MutableLiveData(SnapshotStateList<Servico>())

    val errorMessage = MutableLiveData("")

    fun getServicos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicos()
                if (response.isSuccessful) {
                    servicos.value!!.clear()
                    servicos.value!!.addAll(response.body() ?: emptyList())

                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("ViewModel", "Erro na HTTP: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao logar")
            } catch (e: Exception) {
                Log.e("ViewModel", "Excecao: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao salvar o filme")
            }
        }
    }

}
