package com.example.ethosconnections.viewmodel.prestadora

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.EmpresaNova
import com.example.ethosconnections.models.Prestadora
import com.example.ethosconnections.models.PrestadoraNova
import com.example.ethosconnections.repositories.PrestadoraRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class PrestadoraViewModel(
    private val context: Context,
    private val repository: PrestadoraRepository
) : ViewModel() {
    val prestadoras = MutableLiveData(SnapshotStateList<Prestadora>())
    val prestadora = MutableLiveData<Prestadora>()
    val empresa = MutableLiveData<Prestadora>()
    val errorMessage = MutableLiveData<String>()

    fun postPrestadora(idEmpresa: UUID, statusAprovacao: String, token: String, callback: (Boolean) -> Unit
    ) {
        val prestadoraNova = PrestadoraNova(idEmpresa, statusAprovacao)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.postPrestadora(
                    prestadoraNova,
                    token = "Bearer $token"
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        errorMessage.value = ""
                        callback(true)

                    } else {
                        errorMessage.value = response.errorBody()?.string()
                            ?: context.getString(R.string.erro_desconhecido)
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

    fun getPrestadoras(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getPrestadoras("Bearer $token")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        prestadoras.value!!.clear()
                        prestadoras.value!!.addAll(response.body() ?: emptyList())
                        errorMessage.value = ""

                    } else {
                        errorMessage.value = response.errorBody()?.string()
                            ?: context.getString(R.string.erro_desconhecido)
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_http)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                }
            }
        }
    }

    fun getPrestadoraPorId(id: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getPrestadoraPorId(id, "Bearer $token")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        prestadora.value = response.body()
                        errorMessage.value = ""
                    } else {
                        errorMessage.value = response.errorBody()?.string()
                            ?: context.getString(R.string.erro_desconhecido)
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_http)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: context.getString(R.string.erro_exception)
                }
            }
        }
    }

    fun deletePrestadora(id: UUID,token: String, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
            callback(false)
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.deletePrestadora(id, "Bearer $token")
                if (response.isSuccessful) {
                    errorMessage.postValue("")
                    callback(true)
                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido))
                    callback(false)
                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
                callback(false)
            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
                callback(false)
            }
        }
    }
}
