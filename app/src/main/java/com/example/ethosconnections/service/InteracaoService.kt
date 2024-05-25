package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Interacao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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
        @Header("Authorization") token: String
    ): Response<Interacao>

    @GET("v1.0/interacoes/{fkEmpresa}")
    suspend fun getInteracoesByFkEmpresa(
        @Path("fkEmpresa") fkEmpresa: UUID,
        @Header("Authorization") token: String
    ): Response<List<Interacao>>

}