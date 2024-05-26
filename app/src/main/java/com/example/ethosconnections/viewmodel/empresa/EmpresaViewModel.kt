package com.example.ethosconnections.viewmodel.empresa

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.EmpresaNova
import com.example.ethosconnections.models.LoginToken
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.repositories.TokenRepository
import com.example.ethosconnections.service.PrestadoraService
import com.example.ethosconnections.service.TokenService
import com.example.ethosconnections.viewmodel.token.TokenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class EmpresaViewModel(private val context: Context, private val repository: EmpresaRepository,private val empresaDataStore: EmpresaDataStore
) : ViewModel() {

    private val prestadoraRepository: PrestadoraRepository by lazy {
        PrestadoraRepository(
            PrestadoraService.create()
        )
    }

    private val tokenViewModel: TokenViewModel by lazy {
        TokenViewModel(context, TokenRepository(TokenService.create()), empresaDataStore)
    }

    val empresa = MutableLiveData<Empresa>()
    val errorMessage = MutableLiveData<String>()

    fun loginEmpresa(email: String, senha: String, callback: (Boolean) -> Unit) {
        tokenViewModel.loginAutenticacao("admin@ethos", "123") { isSuccess ->
            if (isSuccess) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val token = empresaDataStore.getToken()
                        val response = repository.loginEmpresa(email, senha, "Bearer $token")
                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {
                                val empresa = response.body()
                                empresa?.let {
                                    empresaDataStore.saveEmpresa(it)
                                    verificaSeEmpresaEPrestadora(it, callback)
                                }
                            } else {
                                errorMessage.value = response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                                callback(false)
                            }
                        }
                    } catch (e: HttpException) {
                        withContext(Dispatchers.Main) {
                            errorMessage.value = e.message ?: context.getString(R.string.erro_http)
                            callback(false)
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                            callback(false)
                        }
                    }
                }
            } else {
                callback(false)
                errorMessage.value = tokenViewModel.errorMessage.value
            }
        }
    }

    private fun verificaSeEmpresaEPrestadora(empresa: Empresa, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = empresaDataStore.getToken()
                val response = prestadoraRepository.getPrestadoras("Bearer $token")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val prestadoras = response.body() ?: emptyList()
                        val isPrestadora = prestadoras.any { it.fkEmpresa == empresa.id }
                        if (isPrestadora) {
                            empresa.plano = "Provider"
                            empresa.idPrestadora = prestadoras.find { it.fkEmpresa == empresa.id }?.fkEmpresa
                        } else {
                            empresa.plano = "Free"
                        }
                        empresaDataStore.saveEmpresa(empresa)
                        this@EmpresaViewModel.empresa.value = empresa
                        callback(true)
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                        callback(false)
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_http)
                    callback(false)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                    callback(false)
                }
            }
        }
    }

    fun getPrestadoraPorId(id: UUID) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = empresaDataStore.getToken()
                val response = repository.getEmpresaPorId(id,"Bearer $token")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        empresa.value = response.body()
                        errorMessage.value = ""
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_desconhecido)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_desconhecido)
                }
            }
        }
    }

    fun cadastrarEmpresa(
        nomeEmpresa: String,
        cnpj: String,
        telefone: String,
        email: String,
        senha: String,
        setor: String,
        qtdFuncionarios: Int,
        assinanteNewsletter: Boolean,
        callback: (Boolean) -> Unit
    ) {


        val empresaNova = EmpresaNova(nomeEmpresa,cnpj, telefone, email, senha, setor, qtdFuncionarios, assinanteNewsletter)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = empresaDataStore.getToken()
                val response = repository.cadastrarEmpresa(empresaNova, "Bearer $token"
                )
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    errorMessage.value =
                        response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                    callback(false)
                }
            } catch (e: Exception) {
                callback(false)
                errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
            }
        }
    }

}


