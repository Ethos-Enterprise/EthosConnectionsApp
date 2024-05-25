package com.example.ethosconnections.viewmodel.token

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TokenViewModel constructor(private val repository: TokenRepository, private val empresaDataStore: EmpresaDataStore): ViewModel(){
    val token= MutableLiveData<Token>()
    val errorMessage = MutableLiveData("")

    fun loginAutenticacao(email: String, senha: String, callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.loginAutenticacao(email, senha)
                if (response.isSuccessful) {
                    val jwtToken = response.body()?.token ?: ""
                    empresaDataStore.saveToken(Token(jwtToken))
                    token.postValue(Token(jwtToken))
                    withContext(Dispatchers.Main) {
                        errorMessage.value = ""
                        callback(true)
                    }
                } else {
                    val error = response.errorBody()?.string() ?: "Erro desconhecido"
                    withContext(Dispatchers.Main) {
                        errorMessage.value = error
                        callback(false)
                    }
                }
            } catch (e: HttpException) {
                val error = e.message ?: "Erro"
                withContext(Dispatchers.Main) {
                    errorMessage.value = error
                    callback(false)
                }
            } catch (e: Exception) {
                val error = e.message ?: "Exception ao pegar token"
                withContext(Dispatchers.Main) {
                    errorMessage.value = error
                    callback(false)
                }
            }
        }
    }
}
