package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.service.PortfolioService
import java.util.UUID

class PortfolioRepository constructor(private val service: PortfolioService) {
    suspend fun getPortfolioById(id:UUID, token : String) = service.getPortfolioById(id,token)

    suspend fun putPortfolioById(id:UUID, request: Portfolio,token : String) = service.putPortfolioById(id, request, token)
}