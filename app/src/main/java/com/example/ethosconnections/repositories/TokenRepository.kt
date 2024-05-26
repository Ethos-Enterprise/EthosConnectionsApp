package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.LoginToken
import com.example.ethosconnections.service.TokenService


class TokenRepository constructor(private val service: TokenService) {

    suspend fun loginAutenticacao( loginToken: LoginToken) = service.loginAutenticacao(loginToken)
}