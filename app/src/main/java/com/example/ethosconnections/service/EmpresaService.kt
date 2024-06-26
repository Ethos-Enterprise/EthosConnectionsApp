package com.example.ethosconnections.service

import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.EmpresaNova
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface EmpresaService {
    companion object {
        fun create(): EmpresaService {
            return Api.getInstance().create(EmpresaService::class.java)
        }
    }

    @GET("v1.0/empresas/login/{email}/{senha}")
    suspend fun loginEmpresa(
        @Path("email") email: String,
        @Path("senha") senha: String,
        @Header("Authorization") token: String
    ): Response<Empresa>

    @GET("v1.0/empresas/{id}")
    suspend fun getEmpresPorId(
        @Path("id") id: UUID,
        @Header("Authorization") token: String
    ): Response<Empresa>

    @POST("v1.0/empresas")
    suspend fun cadastrarEmpresa(
        @Body empresaNova: EmpresaNova,
        @Header("Authorization") token: String
    ): Response<Empresa>

}