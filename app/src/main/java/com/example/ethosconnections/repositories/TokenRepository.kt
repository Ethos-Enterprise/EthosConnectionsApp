package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.TokenService


class TokenRepository constructor(private val service: TokenService) {

    suspend fun loginAutenticacao( email: String,senha: String) = service.loginAutenticacao(email, senha)
}