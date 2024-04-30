package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Portfolio
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface PortfolioService {
    companion object {
        fun create(): PortfolioService {
            return Api.getInstance().create(PortfolioService::class.java)
        }
    }
    @GET("v1.0/portfolios/{id}")
    suspend fun getPortfolioById(@Path("id") id: UUID): Response<Portfolio>

    @PUT("v1.0/portfolios/{id}")
    suspend fun putPortfolioById(@Path("id") id: UUID, @Body request: Portfolio): Response<Portfolio>

}