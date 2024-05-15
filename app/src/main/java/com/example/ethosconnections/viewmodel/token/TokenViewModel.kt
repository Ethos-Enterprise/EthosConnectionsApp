package com.example.ethosconnections.viewmodel.token

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TokenViewModel constructor(private val repository: TokenRepository): ViewModel(){
    val token= MutableLiveData<Token>()
    val errorMessage = MutableLiveData("")

    fun loginAutenticacao( email: String, senha:String) {
        CoroutineScope(Dispatchers.IO ).launch {
            try {
                val response = repository.loginAutenticacao(email, senha)
                if (response.isSuccessful) {
                    errorMessage.postValue("")
                    Log.e("PEGUEI TOKEN" ,"olha: ${response.body()?.token}")
                    response.body()?.token

                } else {
                    errorMessage.postValue(response.errorBody()?.string() ?: "nao sei erro")
                    Log.e("TokenViewModel", "ELSE: ${errorMessage.value}")

                }
            } catch (e: HttpException) {
                errorMessage.postValue(e.message ?: "erro na meta")
                Log.e("TokenViewModel", "HTTP Error: ${errorMessage.value}")

            } catch (e: Exception) {
                errorMessage.postValue(e.message ?: "Exception ao pegar token")
                Log.e("TokenViewModel", "Exception: ${errorMessage.value}")

            }
        }
    }
}