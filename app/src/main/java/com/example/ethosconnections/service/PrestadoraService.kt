package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Prestadora
import com.example.ethosconnections.models.PrestadoraNova
import com.example.ethosconnections.models.Servico
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface PrestadoraService {
    companion object {
        fun create(): PrestadoraService {
            return Api.getInstance().create(PrestadoraService::class.java)
        }
    }
    @POST("/v1.0/prestadoras")
    suspend fun postPrestadora(
        @Body prestadoraNova: PrestadoraNova,
        @Header("Authorization") token: String
    ): Response<Void>
    @GET("v1.0/prestadoras")
    suspend fun getPrestadoras(@Header("Authorization") token: String): Response<List<Prestadora>>

    @GET("v1.0/prestadoras/{id}")
    suspend fun getPrestadoraPorId(@Path("id") id:UUID, @Header("Authorization") token: String): Response<Prestadora>

}