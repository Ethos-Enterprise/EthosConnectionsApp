package com.example.ethosconnections.service

import com.example.ethosconnections.api.Api
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Token
import retrofit2.Response
import retrofit2.http.POST

interface TokenService {

    companion object {
        fun create(): TokenService {
            return Api.getInstance().create(TokenService::class.java)
        }
    }

    @POST("v1.0/auth/login")
    suspend fun loginAutenticacao(
        email: String,
        senha: String,
    ): Response<Token>

}