package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Servico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.util.UUID

interface ServicoService {
    companion object {
        fun create(): ServicoService{
            return Api.getInstance().create(ServicoService::class.java)
        }
    }
    @GET("v1.0/servicos")
    suspend fun getServicos(@Header("Authorization") token: String): Response<List<Servico>>

    @GET("v1.0/servicos/{id}")
    suspend fun getServicoById(@Path("id") id:UUID, @Header("Authorization") token: String): Response<Servico>

}