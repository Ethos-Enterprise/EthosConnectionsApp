package com.example.ethosconnections.viewmodel.servico

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.service.PrestadoraService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class ServicoViewModel constructor(private val context: Context, private val repository: ServicoRepository): ViewModel(){

    private val prestadoraRepository: PrestadoraRepository by lazy {
        PrestadoraRepository(
            PrestadoraService.create()
        )
    }

    private val empresaRepository: EmpresaRepository by lazy {
        EmpresaRepository(
            EmpresaService.create()
        )
    }

    val servicos = MutableLiveData(SnapshotStateList<Servico>())
    val servico= MutableLiveData<Servico>()
    val errorMessage = MutableLiveData("")

    fun getServicos(token:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicos("Bearer $token")
                if (response.isSuccessful) {
                    val servicosList = response.body() ?: emptyList()
                    Log.e("GETSERVICOS", servicosList.toString())

                    for (servico in servicosList) {
                        val nomeServicoAtual = getNomeEmpresaServico(servico, token)
                        servico.nomeEmpresa = nomeServicoAtual
                    }

                    withContext(Dispatchers.Main) {
                        servicos.value!!.clear()
                        servicos.value!!.addAll(servicosList)
                        errorMessage.postValue("")
                    }
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

    fun getServicoById(id: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicoById(id, "Bearer $token")
                if (response.isSuccessful) {
                    val response = response.body()
                    if (response != null) {
                        val nomeServicoAtual = getNomeEmpresaServico(response,  token)
                        response.nomeEmpresa = nomeServicoAtual

                    } else {
                        errorMessage.postValue(context.getString(R.string.nao_encontrado))
                    }
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
                Log.e("servicoViewModel", errorMessage.toString())
            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
                Log.e("servicoViewModel", errorMessage.toString())

            }
        }
    }

    suspend fun getNomeEmpresaServico(servico: Servico, token: String): String {
        return try {
            val response = prestadoraRepository.getPrestadoraPorId(servico.fkPrestadoraServico, "Bearer $token")
            if (response.isSuccessful) {
                val prestadora = response.body()
                Log.e("getNomeEmpresaServico , pegou prestadora", prestadora.toString())


                if (prestadora != null) {

                    val empresaResponse = empresaRepository.getEmpresaPorId(prestadora!!.fkEmpresa!!, "Bearer $token")
                    if (empresaResponse.isSuccessful) {
                        Log.e("getNomeEmpresaServico , pegou empresa", empresaResponse.toString())

                        val empresa = empresaResponse.body()
                        empresa?.razaoSocial ?: context.getString(R.string.nao_encontrado)
                    } else {
                        Log.e("getNomeEmpresaServico , nao pegou empresa", empresaResponse.toString())

                        "${empresaResponse.message()}"
                    }
                } else {
                    context.getString(R.string.nao_encontrado)
                }
            } else {
                " ${response.message()}"
            }
        } catch (e: Exception) {
            context.getString(R.string.erro_exception)
        }
    }
}
