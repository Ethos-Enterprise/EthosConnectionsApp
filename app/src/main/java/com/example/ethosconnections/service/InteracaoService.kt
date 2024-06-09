package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Interacao
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface InteracaoService {

    companion object {
        fun create(): InteracaoService {
            return Api.getInstance().create(InteracaoService::class.java)
        }
    }

    @POST("v1.0/interacoes")
    suspend fun postInteracao(
        @Body interacaoRequest: InteracaoViewModel.InteracaoRequest,
        @Header("Authorization") token: String
    ): Response<Interacao>

    @GET("v1.0/interacoes/empresa/{fkEmpresa}")
    suspend fun getInteracoesByFkEmpresa(
        @Path("fkEmpresa") fkEmpresa: UUID,
        @Header("Authorization") token: String
    ): Response<List<Interacao>>

    @GET("v1.0/interacoes/servico/{fkServico}")
    suspend fun getInteracoesByServico(
        @Path("fkServico") fkPrestadora: UUID,
        @Header("Authorization") token: String
    ): Response<List<Interacao>>
}