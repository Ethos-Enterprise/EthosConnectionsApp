package com.example.ethosconnections.viewmodel.avaliacao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Avaliacao
import com.example.ethosconnections.repositories.AvaliacaoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AvaliacaoViewModel(private val repository: AvaliacaoRepository) : ViewModel() {
    val meta = MutableLiveData<Avaliacao>()
    val allAvaliacoes = MutableLiveData<List<Avaliacao>>()
    val errorMessage = MutableLiveData<String>()
    fun getAllAvaliacoes(token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("MetaViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getAllAvaliacoes("Bearer $token")
                if (response.isSuccessful) {
                    allAvaliacoes.postValue(response.body())
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("MetaViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error fetching all Avaliacoes")
            } catch (e: Exception) {
                Log.e("MetaViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while fetching all Avaliacoes")
            }
        }
    }
}