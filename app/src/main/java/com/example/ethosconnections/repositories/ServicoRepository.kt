package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.ServicoService

class ServicoRepository constructor(private val service: ServicoService) {
    suspend fun getServicos() = service.getServicos()
}