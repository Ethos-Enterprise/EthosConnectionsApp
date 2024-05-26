package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.TokenService


class TokenRepository constructor(private val service: TokenService) {

    suspend fun loginAutenticacao( email: String,password: String) = service.loginAutenticacao(email, password)
}