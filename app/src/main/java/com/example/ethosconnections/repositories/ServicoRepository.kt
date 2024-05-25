package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.ServicoService
import java.util.UUID

class ServicoRepository constructor(private val service: ServicoService) {
    suspend fun getServicos(token : String) = service.getServicos(token)

    suspend fun getServicoById(id: UUID, token : String) = service.getServicoById(id, token)

}