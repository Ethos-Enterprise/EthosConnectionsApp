package com.example.ethosconnections.viewmodel.portfolio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.PortfolioRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

class PortfolioViewModel(private val repository: PortfolioRepository) : ViewModel() {
    val portfolio = MutableLiveData<Portfolio>()
    val errorMessage = MutableLiveData<String>()

    fun getPortfolio(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("ViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
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
                Log.e("ViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error loading Portfolio by ID")
            } catch (e: Exception) {
                Log.e("ViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while fetching Portfolio by ID")
            }
        }
    }

    fun putPortfolio(id: UUID, portfolio: Portfolio, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("ViewModel", "Error: ${throwable.message}")
            errorMessage.postValue(throwable.message ?: "Unknown error occurred")
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
                Log.e("ViewModel", "HTTP Error: ${e.message}")
                errorMessage.postValue(e.message ?: "Error updating Portfolio by ID")
            } catch (e: Exception) {
                Log.e("ViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception while updating Portfolio by ID")
            }
        }
    }
}
