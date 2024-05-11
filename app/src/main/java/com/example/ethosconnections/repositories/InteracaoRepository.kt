package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.InteracaoService
import java.util.UUID

class InteracaoRepository constructor(private val service: InteracaoService) {

     suspend fun postInteracao(
         status: String,
         fkServico: UUID,
         fkEmpresa: UUID,
    ) = service.postInteracao(status, fkServico, fkEmpresa)
}