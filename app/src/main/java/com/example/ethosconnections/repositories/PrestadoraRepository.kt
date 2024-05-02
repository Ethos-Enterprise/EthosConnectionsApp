package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.PrestadoraService
import java.util.UUID


class PrestadoraRepository constructor(private val service: PrestadoraService) {
    suspend fun getPrestadoras() = service.getPrestadoras()

    suspend fun getPrestadoraPorId(id:UUID) = service.getPrestadoraPorId(id)

}