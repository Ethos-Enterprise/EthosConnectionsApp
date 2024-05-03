package com.example.ethosconnections.viewmodel.meta

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.repositories.MetaRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

class MetaViewModel(private val repository: MetaRepository) : ViewModel() {
    val meta = MutableLiveData<Meta>()
    val allMetas = MutableLiveData<List<Meta>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMetas() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getAllMetas()
                if (response.isSuccessful) {
                    allMetas.postValue(response.body())
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

    fun getMetaById(id: UUID) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getMetaById(id)
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

    fun postMeta(meta: Meta, callback: (Boolean) -> Unit) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                // Salvando o método no banco de dados
                repository.postMeta(meta)

                val response = repository.postMeta(meta)
                if (response.isSuccessful) {
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error posting Meta")
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while posting Meta")
            }
        }
    }
}
