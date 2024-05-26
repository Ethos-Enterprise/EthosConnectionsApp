package com.example.ethosconnections.viewmodel.token

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.LoginToken
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TokenViewModel constructor(private val context: Context, private val repository: TokenRepository, private val empresaDataStore: EmpresaDataStore): ViewModel(){
    val token= MutableLiveData<Token>()
    val errorMessage = MutableLiveData("")

    fun loginAutenticacao(email: String, password: String, callback: (Boolean) -> Unit) {
        val loginToken = LoginToken(email, password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.loginAutenticacao(loginToken)
                if (response.isSuccessful) {
                    val jwtToken = response.body()?.token ?: ""
                    empresaDataStore.saveToken(Token(jwtToken))
                    token.postValue(Token(jwtToken))
                    withContext(Dispatchers.Main) {
                        errorMessage.value = ""
                        callback(true)
                    }

                } else {
                    val error = response.errorBody()?.string() ?: context.getString(R.string.erro_desconhecido)
                    withContext(Dispatchers.Main) {
                        errorMessage.value = error
                        callback(false)
                    }
                }
            } catch (e: HttpException) {
                val error = e.message ?: context.getString(R.string.erro_http)
                withContext(Dispatchers.Main) {
                    errorMessage.value = error
                    callback(false)
                }
            } catch (e: Exception) {
                val error = e.message ?: context.getString(R.string.erro_exception)
                withContext(Dispatchers.Main) {
                    errorMessage.value = error
                    callback(false)

                }
            }
        }
    }
}
