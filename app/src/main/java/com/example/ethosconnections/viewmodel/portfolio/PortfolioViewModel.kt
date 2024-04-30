package com.example.ethosconnections.viewmodel.portfolio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.repositories.PortfolioRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.UUID

class PortfolioViewModel constructor(private val repository: PortfolioRepository): ViewModel(){
    val portfolios = MutableLiveData<Portfolio>()

    val errorMessage = MutableLiveData("")

    fun getPortfolio(id:UUID) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getPortfolioById(id)
                if (response.isSuccessful) {
                    //portfolios.value!!.clear()
                    //portfolios.value!!.addAll(response.body() ?: emptyList())

                    errorMessage.postValue("")
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            } catch (e: HttpException) {
                Log.e("ViewModel", "Erro na HTTP: ${e.message}")
                errorMessage.postValue(e.message ?: "Erro ao carregar Portfolio por ID")
            } catch (e: Exception) {
                Log.e("ViewModel", "Exception: ${e.message}")
                errorMessage.postValue(e.message ?: "Exception ao buscar Portfolio por ID")
            }
        }
    }

}