package com.example.ethosconnections.viewmodel.servico

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class ServicoViewModel constructor(private val repository: ServicoRepository): ViewModel(){

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

    val errorMessage = MutableLiveData("")

    fun getServicos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getServicos()
                if (response.isSuccessful) {
                    val servicosList = response.body() ?: emptyList()
                    for (servico in servicosList) {
                        val nomeServicoAtual = getNomeEmpresaServico(servico)
                        servico.nomeEmpresa = nomeServicoAtual
                        Log.e("ServicoViewModel", "nome: ${servico.nomeEmpresa}")
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
                Log.e("ViewModel", "Erro na HTTP: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao logar")
            } catch (e: Exception) {
                Log.e("ViewModel", "Excecao: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao salvar o filme")
            }
        }
    }

    suspend fun getNomeEmpresaServico(servico: Servico): String {
        return try {
            val response = prestadoraRepository.getPrestadoraPorId(servico.fkPrestadoraServico)
            if (response.isSuccessful) {
                val prestadora = response.body()

                if (prestadora != null) {

                    val empresaResponse = empresaRepository.getEmpresaPorId(prestadora!!.fkEmpresa!!)
                    if (empresaResponse.isSuccessful) {

                        val empresa = empresaResponse.body()
                        empresa?.razaoSocial ?: "Empresa não encontrada"
                    } else {
                        "Erro ao obter o nome da empresa: ${empresaResponse.message()}"
                    }
                } else {
                    "Prestadora não encontrada"
                }
            } else {
                "Erro ao obter a prestadora: ${response.message()}"
            }
        } catch (e: Exception) {
            Log.e("ServicoViewModel", "Erro ao obter o nome da empresa para o serviço: ${e.message}")
            "Erro ao obter o nome da empresa"
        }
    }
}
