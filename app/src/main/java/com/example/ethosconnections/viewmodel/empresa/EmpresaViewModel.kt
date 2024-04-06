package com.example.ethosconnections.viewmodel.empresa

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpresaViewModel constructor(private val repository: EmpresaRepository) : ViewModel(){

    val empresa = MutableLiveData<Empresa>()
    val errorMessage = MutableLiveData<String>()

//    fun loginEmpresa(email: String, senha: String) {
//        val request = repository.loginEmpresa(email, senha)
//        request.enqueue(object : Callback<Empresa> {
//            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
//                if (response.isSuccessful) {
//                    empresa.postValue(response.body())
//
//                    Log.i("ViewModel", "Resposta bem-sucedida: $empresa")
//
//                    navController.navigate("plataforma")
//
//                } else {
//                    val errorCode = response.code()
//                    Log.e("ViewModel", "Erro na resposta da API. Código: $errorCode, Mensagem: $errorMessage")
//
//                    errorMessage.postValue("Erro ao fazer login: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<Empresa>, t: Throwable) {
//                //se nao der certo
//                Log.e("ViewModel", "Falha na chamada de API: ${t.message}", t)
//
//                errorMessage.postValue(t.message)
//            }
//        }
//        )
//    }

    //TESTE MOCK API
    fun loginEmpresa() {
        val request = repository.loginEmpresa()

        request.enqueue(object : Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                if (response.isSuccessful) {
                    empresa.postValue(response.body())

                    Log.i("ViewModel", "Resposta bem-sucedida: $empresa")


                } else {
                    val errorCode = response.code()
                    Log.e("ViewModel", "Erro na resposta da API. Código: $errorCode, Mensagem: $errorMessage")

                    errorMessage.postValue("Erro ao fazer login: ${response.message()}")
                }

            }
            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                //se nao der certo
                Log.e("ViewModel", "Falha na chamada de API: ${t.message}", t)

                errorMessage.postValue(t.message)
            }

        }
        )
    }
}