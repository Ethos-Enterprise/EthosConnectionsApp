package com.example.ethosconnections.viewmodel.servico

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.repositories.TokenRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.service.PrestadoraService
import com.example.ethosconnections.service.TokenService
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import com.example.ethosconnections.viewmodel.token.TokenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class ServicoViewModel constructor(private val context: Context, private val repository: ServicoRepository, val empresaDataStore: EmpresaDataStore): ViewModel(){

    private val prestadoraRepository: PrestadoraRepository by lazy {
        PrestadoraRepository(
            PrestadoraService.create()
        )
    }

    private val empresaViewModel: EmpresaViewModel by lazy {
        EmpresaViewModel(context, EmpresaRepository(EmpresaService.create()), empresaDataStore)
    }
    val servicos = MutableLiveData(SnapshotStateList<Servico>())
    val servico = MutableLiveData<Servico>()
    val errorMessage = MutableLiveData("")

    fun getServicos(token:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicos("Bearer $token")
                if (response.isSuccessful) {
                    val servicosList = response.body() ?: emptyList()
                    for (servico in servicosList) {
                            val nomeServicoAtual = getNomeEmpresaServico(servico, token)
                            servico.nomeEmpresa = nomeServicoAtual
                            servico.idEmpresa = empresaViewModel.empresa.value?.id ?: UUID.randomUUID()

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

    suspend fun getServicoById(id: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicoById(id, "Bearer $token")
                if (response.isSuccessful) {
                    val servicoResponse = response.body()!!
                    withContext(Dispatchers.Main) {
                        servico.postValue(servicoResponse)
                        Log.e("OK getservicobyid", servicoResponse.toString())
                    }
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                    Log.e("else getservicobyid", errorMessage.toString())
                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
                Log.e("else getservicobyid", e.message.toString())

            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
                Log.e("else getservicobyid", e.message.toString())

            }
        }
    }

    suspend fun getNomeEmpresaServico(servico: Servico, token: String): String {
        return try {
            val response = prestadoraRepository.getPrestadoraPorId(servico.fkPrestadoraServico, "Bearer $token")
            if (response.isSuccessful) {
                val prestadora = response.body()

                if (prestadora != null) {
                    var empresaResponse = empresaViewModel.getEmpresaById(prestadora.fkEmpresa!!, token)
                    delay(1000)
                    var nomeEmpresa = ""
                    if(empresaViewModel.empresa.value?.razaoSocial.toString() != null) {
                         nomeEmpresa = empresaViewModel.empresa.value?.razaoSocial.toString()
                    }
                    nomeEmpresa
                } else {
                    context.getString(R.string.nao_encontrado)
                }
            } else {
                context.getString(R.string.nao_encontrado)
            }
        } catch (e: Exception) {
            context.getString(R.string.erro_exception)
        }
    }

}
