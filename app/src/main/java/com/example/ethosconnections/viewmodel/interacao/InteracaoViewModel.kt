package com.example.ethosconnections.viewmodel.interacao

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Interacao
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.InteracaoRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.EmpresaService
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

    private val servicoRepository: ServicoRepository by lazy {
        ServicoRepository(
            ServicoService.create()
        )
    }

    private val empresaRepository: EmpresaRepository by lazy {
        EmpresaRepository(
            EmpresaService.create()
        )
    }

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
                val response =
                    repository.postInteracao(status, fkServico, fkEmpresa, "Bearer $token")

                if (response.isSuccessful) {
                    callback(true)
                } else {
                    errorMessage.value = response.errorBody()?.string()
                        ?: context.getString(R.string.erro_desconhecido)
                    callback(false)
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                callback(false)

            }
        }
    }

    suspend fun getInteracoesByFkEmpresa(fkEmpresa: UUID, token: String) {
        try {
            val response = repository.getInteracoesByFkEmpresa(fkEmpresa, "Bearer $token")

            if (response.isSuccessful) {
                val interacoesList = response.body() ?: emptyList()

                for (interacao in interacoesList) {
                    val servicoViewModel =
                        ServicoViewModel(context, ServicoRepository(ServicoService.create()))

                    val servico = withContext(Dispatchers.IO) {
                        servicoViewModel.getServicoById(interacao.fkServico, token)
                        servicoViewModel.servico.value
                    }
                    interacao.nomeServico = servico?.nomeServico ?: context.getString(R.string.nao_encontrado)
                    interacao.nomeEmpresa = context.getString(R.string.nao_encontrado)
                }

                withContext(Dispatchers.Main) {
                    interacoes.value!!.clear()
                    interacoes.value!!.addAll(interacoesList)
                    errorMessage.postValue("")
                    Log.e("OK getInteracoesByFkEmpresa", interacoesList.toString())
                }
            } else {
                errorMessage.postValue(response.errorBody()?.string())
                Log.e("else getInteracoesByFkEmpresa", errorMessage.value.toString())
            }
        } catch (e: HttpException) {
            errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
            Log.e("http getInteracoesByFkEmpresa", errorMessage.value.toString())
        } catch (e: Exception) {
            errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
            Log.e("exception getInteracoesByFkEmpresa", e.message.toString())
        }
    }


    fun getInteracoesServicos(fkPrestadora: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val responseServicos = servicoRepository.getServicos("Bearer $token")
                if (responseServicos.isSuccessful) {
                    val servicosPrestadora = responseServicos.body()
                        ?.filter { servico -> servico.fkPrestadoraServico == fkPrestadora }

                    val todasInteracoes = mutableListOf<Interacao>()

                    servicosPrestadora?.let {
                        for (servicoPrestadora in it) {
                            val interacoesResponse = repository.getInteracoesByServico(
                                servicoPrestadora.id,
                                "Bearer $token"
                            )
                            if (interacoesResponse.isSuccessful) {
                                for (interacao in interacoesResponse.body()!!) {
                                    val empresaResponse = empresaRepository.getEmpresaPorId(
                                        interacao.fkEmpresa,
                                        "Bearer $token"
                                    )
                                    if (empresaResponse.isSuccessful) {
                                        val empresa = empresaResponse.body()
                                        todasInteracoes.add(
                                            Interacao(
                                                interacao.id,
                                                interacao.status,
                                                interacao.data,
                                                interacao.fkServico,
                                                interacao.fkEmpresa,
                                                empresa?.razaoSocial
                                                    ?: context.getString(R.string.nao_encontrado),
                                                servicoPrestadora.nomeServico
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        interacoes.value!!.addAll(todasInteracoes)
                    }
                    Log.e("a", todasInteracoes.toString())

                } else {
                    errorMessage.postValue(context.getString(R.string.erro_desconhecido))
                    Log.e("a", errorMessage.toString())

                }
            } catch (e: Exception) {
                errorMessage.postValue(context.getString(R.string.erro_exception))
                Log.e("a", errorMessage.toString())

            }
        }
    }
}