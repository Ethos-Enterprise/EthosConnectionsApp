package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Prestadora
import com.example.ethosconnections.models.Servico
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface PrestadoraService {
    companion object {
        fun create(): PrestadoraService {
            return Api.getInstance().create(PrestadoraService::class.java)
        }
    }
    @GET("v1.0/prestadoras")
    suspend fun getPrestadoras(): Response<List<Prestadora>>

    @GET("v1.0/prestadoras/{id}")
    suspend fun getPrestadoraPorId(@Path("id") id:UUID): Response<Prestadora>

}