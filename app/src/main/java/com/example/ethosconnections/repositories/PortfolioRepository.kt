package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.service.PortfolioService
import java.util.UUID

class PortfolioRepository constructor(private val service: PortfolioService) {
    suspend fun getPortfolioById(id:UUID) = service.getPortfolioById(id)

    suspend fun putPortfolioById(id:UUID, request: Portfolio) = service.putPortfolioById(id, request)
}