package com.example.ethosconnections.viewmodel.meta

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class MetaViewModel(private val repository: MetaRepository) : ViewModel() {
    val meta = MutableLiveData<Meta>()
    val allMetas = MutableLiveData(SnapshotStateList<Meta>())

    val errorMessage = MutableLiveData<String>()

    fun getAllMetas(token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getAllMetas("Bearer $token")
                if (response.isSuccessful) {
                    val metas = response.body() ?: emptyList()
                    Log.d("MetaViewModel", "Metas retornadas: $metas")
                    allMetas.value!!.clear()
                    allMetas.value!!.addAll(metas)
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error fetching all Metas")
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while fetching all Metas")
            }
        }
    }

    fun getMetasByFkEmpresa(fkEmpresa: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getMetasByFkEmpresa(fkEmpresa, "Bearer $token")
                if (response.isSuccessful) {
                    val metas = response.body() ?: emptyList()
                    Log.d("MetaViewModel", "Metas retornadas: $metas")
                    allMetas.value!!.clear()
                    allMetas.value!!.addAll(metas)
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error fetching all Metas")
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while fetching all Metas")
            }
        }
    }


    fun getMetaById(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
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
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error loading Meta by ID")
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while fetching Meta by ID")
            }
        }
    }

    fun postMeta(meta: Meta,token: String, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                // Salvando o método no banco de dados
                val response = repository.postMeta(meta, "Bearer $token")
                if (response.isSuccessful) {
                    errorMessage.postValue("")
                    callback(true)
                    Log.e("MetaViewModel", "OK: ${response.body()}")

                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: "Unknown error")
                    callback(false)
                    Log.e("MetaViewModel", "ELSE: ${errorMessage.value}")

                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error posting Meta")

                callback(false)
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while posting Meta")
                callback(false)
            }
        }
    }


    fun deleteMeta(id: UUID,token: String, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
            callback(false)
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.deleteMeta(id, "Bearer $token")
                if (response.isSuccessful) {
                    errorMessage.postValue("")
                    Log.d("MetaViewModel", "Meta deleted successfully")
                    callback(true)
                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: "Unknown error")
                    Log.e("MetaViewModel", "Error deleting Meta: ${errorMessage.value}")
                    callback(false)
                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error deleting Meta")
                callback(false)
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while deleting Meta")
                callback(false)
            }
        }
    }

}
