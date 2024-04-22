package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Servico
import retrofit2.Response
import retrofit2.http.GET

interface ServicoService {
    companion object {
        fun create(): ServicoService{
            return Api.getInstance().create(ServicoService::class.java)
        }
    }
    @GET("v1.0/servicos")
    suspend fun getServicos(): Response<List<Servico>>

}