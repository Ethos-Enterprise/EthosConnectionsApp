package com.example.ethosconnections.viewmodel.meta

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.MetaRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

class MetaViewModel(private val context: Context, private val repository: MetaRepository) : ViewModel() {
    val meta = MutableLiveData<Meta>()
    val allMetas = MutableLiveData(SnapshotStateList<Meta>())

    val errorMessage = MutableLiveData<String>()

    fun getAllMetas(token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getAllMetas("Bearer $token")
                if (response.isSuccessful) {
                    val metas = response.body() ?: emptyList()
                    allMetas.value!!.clear()
                    allMetas.value!!.addAll(metas)
                    errorMessage.postValue("")
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

    fun getMetasByFkEmpresa(fkEmpresa: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getMetasByFkEmpresa(fkEmpresa, "Bearer $token")
                if (response.isSuccessful) {
                    val metas = response.body() ?: emptyList()
                    allMetas.value!!.clear()
                    allMetas.value!!.addAll(metas)
                    errorMessage.postValue("")
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


    fun getMetaById(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getMetaById(id, "Bearer $token")
                if (response.isSuccessful) {
                    meta.postValue(response.body())
                    errorMessage.postValue("")
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

    fun postMeta(meta: Meta,token: String, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                // Salvando o método no banco de dados
                val response = repository.postMeta(meta, "Bearer $token")
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

                Log.e("MetaViewModel", errorMessage.value.toString())
            }
        }
    }


    fun deleteMeta(id: UUID,token: String, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
            callback(false)
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.deleteMeta(id, "Bearer $token")
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
