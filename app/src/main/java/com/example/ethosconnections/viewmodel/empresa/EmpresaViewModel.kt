package com.example.ethosconnections.viewmodel.empresa

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.service.PrestadoraService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class EmpresaViewModel(private val context: Context, private val repository: EmpresaRepository) : ViewModel() {

    val empresaDataStore: EmpresaDataStore = EmpresaDataStore(context)
    private val prestadoraRepository: PrestadoraRepository by lazy {
        PrestadoraRepository(
            PrestadoraService.create()
        )
    }

    val empresa = MutableLiveData<Empresa>()
    val errorMessage = MutableLiveData<String>()

    fun loginEmpresa(email: String, senha: String, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.loginEmpresa(email, senha)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val empresa = response.body()
                        empresa?.let {
                            empresaDataStore.saveEmpresa(it)
                            verificaSeEmpresaEPrestadora(it, callback)
                        }
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

    private fun verificaSeEmpresaEPrestadora(empresa: Empresa, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = prestadoraRepository.getPrestadoras()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val prestadoras = response.body() ?: emptyList()
                        val isPrestadora = prestadoras.any { it.fkEmpresa == empresa.id }
                        if (isPrestadora) {
                            empresa.plano = "provider"
                        } else {
                            empresa.plano = "free"
                        }
                        empresaDataStore.saveEmpresa(empresa)
                        this@EmpresaViewModel.empresa.value = empresa
                        callback(true)
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: "Erro desconhecido"
                        callback(false)
                    }
                }
            } catch (e: HttpException) {
                Log.e("EmpresaViewModel", "Erro na HTTP: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro ao verificar se é prestadora"
                    callback(false)
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Excecao: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro ao verificar se é prestadora"
                    callback(false)
                }
            }
        }
    }

    fun getPrestadoraPorId(id: UUID) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getEmpresaPorId(id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        empresa.value = response.body()
                        errorMessage.value = ""
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: "Erro desconhecido"
                    }
                }
            } catch (e: HttpException) {
                Log.e("PrestadoraViewModel", "Erro na HTTP: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido "
                }
            } catch (e: Exception) {
                Log.e("PrestadoraViewModel", "Excecao: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido"
                }
            }
        }
    }
}
