package com.example.ethosconnections.service

import android.util.Log
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.api.Api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EmpresaService {

    companion object {
        fun create(): EmpresaService {
            return Api.getInstance().create(EmpresaService::class.java)
        }
    }

    @GET("v1.0/empresas/{email}/{senha}")
    fun loginEmpresa(
    @Path("email") email: String,
        @Path("senha") senha: String
    ): Call<Empresa>

    //TESTE MOCK API
//    @GET("/empresas/1")
//    fun loginEmpresa(
//    ): Call<Empresa>

}