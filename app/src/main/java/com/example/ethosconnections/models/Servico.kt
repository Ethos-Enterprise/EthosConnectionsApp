package com.example.ethosconnections.models

import java.util.UUID

data class Servico(
    val id: UUID,
    val nome: String,
    val descricao: String,
    val valor: Double,
    val areaAtuacaoEsg: String,
    val fkPrestadoraServico: UUID
)
