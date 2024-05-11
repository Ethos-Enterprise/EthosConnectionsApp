package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Interacao
import retrofit2.Response
import retrofit2.http.POST
import java.util.UUID

interface InteracaoService {

    companion object {
        fun create(): InteracaoService {
            return Api.getInstance().create(InteracaoService::class.java)
        }
    }

    @POST("v1.0/interacoes")
    suspend fun postInteracao(
        status: String,
        fkServico: UUID,
        fkEmpresa: UUID,
    ): Response<Interacao>
}