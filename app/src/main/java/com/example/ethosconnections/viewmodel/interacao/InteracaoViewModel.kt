package com.example.ethosconnections.viewmodel.interacao

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Interacao
import com.example.ethosconnections.repositories.InteracaoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class InteracaoViewModel(
    private val context: Context,
    private val repository: InteracaoRepository
) : ViewModel() {

    val interacao = MutableLiveData<Interacao>()
    val errorMessage = MutableLiveData<String>()


    fun postInteracao(
        status: String,
        fkServico: UUID,
        fkEmpresa: UUID,
        callback: (Boolean) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.postInteracao(status, fkServico, fkEmpresa)

                if (response.isSuccessful) {
                    val interacaoResponse = response.body()
                    callback(true)
                    Log.e("InteracaoViewModel", "Deu bom: ${interacaoResponse}")
                }else{
                    errorMessage.value= response.errorBody()?.string() ?: "Erro desconhecido"
                    callback(false)
                    Log.e("InteracaoViewModel", "Deu ruim: ${errorMessage}")
                }
            }
            catch (e: Exception) {
                errorMessage.value= e.message?: "Exception"
                callback(false)
                Log.e("interacaoViewModel", "Deu ruim : ${errorMessage}}")
            }
        }
    }

}