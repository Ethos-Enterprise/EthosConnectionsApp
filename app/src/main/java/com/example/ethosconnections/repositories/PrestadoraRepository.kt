package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.PrestadoraService
import java.util.UUID


class PrestadoraRepository constructor(private val service: PrestadoraService) {
    suspend fun getPrestadoras(token : String) = service.getPrestadoras(token)

    suspend fun getPrestadoraPorId(id:UUID, token : String) = service.getPrestadoraPorId(id, token)

}