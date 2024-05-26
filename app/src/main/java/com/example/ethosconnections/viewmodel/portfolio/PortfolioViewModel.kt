package com.example.ethosconnections.viewmodel.portfolio

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.PortfolioRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

class PortfolioViewModel(private val context: Context, private val repository: PortfolioRepository) : ViewModel() {
    val portfolio = MutableLiveData<Portfolio>()
    val errorMessage = MutableLiveData<String>()

    fun getPortfolio(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.getPortfolioById(id, "Bearer $token")
                if (response.isSuccessful) {
                    portfolio.postValue(response.body())
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

    fun putPortfolio(id: UUID, portfolio: Portfolio, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = repository.putPortfolioById(id, portfolio, "Bearer $token")
                if (response.isSuccessful) {
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
