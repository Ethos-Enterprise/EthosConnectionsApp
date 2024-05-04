package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Avaliacao
import com.example.ethosconnections.service.AvaliacaoService
import retrofit2.Response

class AvaliacaoRepository (private val service: AvaliacaoService) {
    suspend fun getAllAvaliacoes(): Response<List<Avaliacao>> {
        return service.getAllAvaliacoes()
    }
}