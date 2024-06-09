package com.example.ethosconnections.viewmodel.portfolio

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.PortfolioDataStore
import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.PortfolioRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class PortfolioViewModel(private val context: Context, private val repository: PortfolioRepository, private val portfolioDataStore: PortfolioDataStore) : ViewModel() {
    val portfolio = MutableLiveData<Portfolio>()
    val errorMessage = MutableLiveData<String>()

    fun getPortfolioById(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }
        viewModelScope.launch(exceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getPortfolioById(id, "Bearer $token")
                }
                if (response.isSuccessful) {
                    portfolio.postValue(response.body())
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido))
                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_http))
            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: context.getString(R.string.erro_exception))
            }
        }
    }

    fun getPortfolioByFkPrestadora(id: UUID, token: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            errorMessage.postValue(throwable.message ?: context.getString(R.string.erro_desconhecido))
        }
        viewModelScope.launch(exceptionHandler) {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getPortfolioByFkPrestadora(id, "Bearer $token")
                }
                if (response.isSuccessful) {
                    portfolio.postValue(response.body())
                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido))
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
