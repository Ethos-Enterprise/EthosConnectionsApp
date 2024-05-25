package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Meta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface MetaService {
    companion object {
        fun create(): MetaService {
            return Api.getInstance().create(MetaService::class.java)
        }
    }

    @GET("v1.0/metas")
    suspend fun getAllMetas(@Header("Authorization") token: String
    ): Response<List<Meta>>

    @GET("v1.0/metas/busca-metas/{fkEmpresa}")
    suspend fun getMetasByFkEmpresa(@Path("fkEmpresa") fkEmpresa: UUID, @Header("Authorization") token: String): Response<List<Meta>>

    @PUT("v1.0/metas/{id}")
    suspend fun getMetaById(@Path("id") id: UUID, @Header("Authorization") token: String): Response<Meta>

    @POST("v1.0/metas")
    suspend fun postMeta(@Body meta: Meta, @Header("Authorization") token: String): Response<Meta>

    @DELETE("v1.0/metas/{id}")
    suspend fun deleteMeta(@Path("id") id: UUID, @Header("Authorization") token: String): Response<Meta>
}