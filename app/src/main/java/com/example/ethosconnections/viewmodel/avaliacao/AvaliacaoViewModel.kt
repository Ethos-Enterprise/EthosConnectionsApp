package com.example.ethosconnections.viewmodel.avaliacao

import android.content.Context
import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Avaliacao
import com.example.ethosconnections.repositories.AvaliacaoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AvaliacaoViewModel(private val context: Context, private val repository: AvaliacaoRepository) : ViewModel() {


    val meta = MutableLiveData<Avaliacao>()
    val allAvaliacoes = MutableLiveData<List<Avaliacao>>()
    val errorMessage = MutableLiveData<String>()
    fun getAllAvaliacoes(token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
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
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))

            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
            }
        }
    }
}