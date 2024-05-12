package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.ServicoService
import java.util.UUID

class ServicoRepository constructor(private val service: ServicoService) {
    suspend fun getServicos() = service.getServicos()

    suspend fun getServicoById(id: UUID) = service.getServicoById(id)

}