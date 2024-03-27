package com.example.ethosconnections.viewmodel.empresa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpresaViewModel constructor(private val repository: EmpresaRepository) : ViewModel(){

    val empresa = MutableLiveData<Empresa>()
    val errorMessage = MutableLiveData<String>()

    fun loginEmpresa() {
        val request  = repository.loginEmpresa()
        request.enqueue(object : Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                //oq fazer se deu certo
                empresa.postValue(response.body())
            }

            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                //se nao der certo
                errorMessage.postValue(t.message)
            }

        }
        )

    }
}