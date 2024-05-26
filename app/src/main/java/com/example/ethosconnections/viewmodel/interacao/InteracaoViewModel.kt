package com.example.ethosconnections.viewmodel.interacao

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Interacao
import com.example.ethosconnections.repositories.InteracaoRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.PrestadoraService
import com.example.ethosconnections.service.ServicoService
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class InteracaoViewModel(
    private val context: Context,
    private val repository: InteracaoRepository
) : ViewModel() {

    val interacao = MutableLiveData<Interacao>()
    val interacoes = MutableLiveData(SnapshotStateList<Interacao>())
    val errorMessage = MutableLiveData<String>()

    fun postInteracao(
        status: String,
        fkServico: UUID,
        fkEmpresa: UUID,
        token: String,
        callback: (Boolean) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.postInteracao(status, fkServico, fkEmpresa, "Bearer $token")

                if (response.isSuccessful) {
                    callback(true)
                }else{
                    errorMessage.value= response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                    callback(false)
                }
            }
            catch (e: Exception) {
                errorMessage.value= e.message?: context.getString(R.string.erro_exception)
                callback(false)
            }
        }
    }


    fun getInteracoesByFkEmpresa(fkEmpresa: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getInteracoesByFkEmpresa(fkEmpresa, "Bearer $token")
                if (response.isSuccessful) {
                    val interacoesList = response.body() ?: emptyList()

                    for (interacao in interacoesList) {
                        val servicoViewModel = ServicoViewModel(context, ServicoRepository(ServicoService.create()))

                        servicoViewModel.getServicoById(interacao.fkServico, "Bearer $token")

                        interacao.nomeServico = servicoViewModel.servico.value?.nomeServico ?: context.getString(R.string.nao_encontrado)
                        interacao.nomeEmpresa = servicoViewModel.servico.value?.nomeEmpresa ?: context.getString(R.string.nao_encontrado)
                    }
                    interacoes.value!!.clear()
                    interacoes.value!!.addAll(interacoesList)

                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
            }
        }
    }


}