package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Avaliacao
import retrofit2.Response
import retrofit2.http.GET

interface AvaliacaoService {
    companion object {
        fun create(): AvaliacaoService {
            return Api.getInstance().create(AvaliacaoService::class.java)
        }
    }

    @GET("/v1.0/avaliacoes")
    suspend fun getAllAvaliacoes(): Response<List<Avaliacao>>
}