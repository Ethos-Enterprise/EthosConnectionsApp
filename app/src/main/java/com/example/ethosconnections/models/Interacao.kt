package com.example.ethosconnections.models

import java.time.LocalDate
import java.util.UUID

data class Interacao(
    var status: String,
    var data: LocalDate,
    var fkServico: UUID,
    var fkEmpresa: UUID
)
