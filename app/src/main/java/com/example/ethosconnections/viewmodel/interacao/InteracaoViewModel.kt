package com.example.ethosconnections.viewmodel.interacao

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
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
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class InteracaoViewModel(
    private val context: Context,
    private val repository: InteracaoRepository,
    private val empresaDataStore: EmpresaDataStore
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

    data class InteracaoRequest(
        val status: String,
        val fkServico: UUID,
        val fkEmpresa: UUID
    )

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
                val interacaoRequest = InteracaoRequest(status, fkServico, fkEmpresa)

                val response = repository.postInteracao(interacaoRequest, "Bearer $token")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        interacao.postValue(response.body())
                        callback(true)
                        Log.e("interacopost", interacao.value.toString())
                    } else {
                        errorMessage.value = response.errorBody()?.string()
                            ?: context.getString(R.string.erro_desconhecido)
                        callback(false)
                        Log.e("interacopost", errorMessage.value.toString())

                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                    callback(false)
                    Log.e("interacopost", errorMessage.value.toString())
                }
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
                        ServicoViewModel(context, ServicoRepository(ServicoService.create()), empresaDataStore)

                    val servico = servicoViewModel.getServicoById(interacao.fkServico, token)
                    servicoViewModel.servico.observeForever { servico ->
                        interacao.nomeServico = servico?.nomeServico ?: context.getString(R.string.nao_encontrado)
                    }

                    val empresaResponse = empresaRepository.getEmpresaPorId(interacao.fkEmpresa, "Bearer $token")
                    val empresa = empresaResponse.body()
                    interacao.nomeEmpresa = empresa?.razaoSocial ?: context.getString(R.string.nao_encontrado)
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

                    servicosPrestadora?.let {
                        val interacoesDeServicos = it.map { servicoPrestadora ->
                            async {
                                val interacoesResponse = repository.getInteracoesByServico(
                                    servicoPrestadora.id,
                                    "Bearer $token"
                                )
                                if (interacoesResponse.isSuccessful) {
                                    interacoesResponse.body()?.map { interacao ->
                                        val empresaResponse = empresaRepository.getEmpresaPorId(
                                            interacao.fkEmpresa,
                                            "Bearer $token"
                                        )
                                        val empresa = empresaResponse.body()
                                        Log.d("EmpresaResponse", "Empresa: ${empresa?.razaoSocial}")

                                        Interacao(
                                            interacao.id,
                                            interacao.status,
                                            interacao.data,
                                            interacao.fkServico,
                                            interacao.fkEmpresa,
                                            empresa?.razaoSocial ?: context.getString(R.string.nao_encontrado),
                                            servicoPrestadora.nomeServico
                                        )
                                    } ?: emptyList()
                                } else {
                                    Log.e("InteracoesResponse", "Failed to get interacoes for servico: ${servicoPrestadora.id}")
                                    emptyList()
                                }
                            }
                        }.awaitAll().flatten()

                        withContext(Dispatchers.Main) {
                            interacoes.value!!.clear()
                            interacoes.value!!.addAll(interacoesDeServicos)
                            errorMessage.postValue("")
                            Log.d("InteracoesDeServicos", interacoesDeServicos.toString())
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        errorMessage.postValue(context.getString(R.string.erro_desconhecido))
                        Log.e("GetServicosResponse", "Failed to get servicos: ${responseServicos.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.postValue(context.getString(R.string.erro_exception))
                    Log.e("GetInteracoesServicosException", e.message.toString())
                }
            }
        }
    }
}
