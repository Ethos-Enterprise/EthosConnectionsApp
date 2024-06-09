package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.EmpresaNova
import com.example.ethosconnections.models.PrestadoraNova
import com.example.ethosconnections.service.PrestadoraService
import java.util.UUID


class PrestadoraRepository constructor(private val service: PrestadoraService) {
    suspend fun getPrestadoras(token : String) = service.getPrestadoras(token)

    suspend fun getPrestadoraPorId(id:UUID, token : String) = service.getPrestadoraPorId(id, token)

    suspend fun postPrestadora(
        prestadoraNova: PrestadoraNova,
        token: String
    ) = service.postPrestadora(prestadoraNova ,token)

}