package com.example.ethosconnections.models

import java.time.LocalDate
import java.util.UUID

data class Interacao(
    var id: UUID,
    var status: String,
    var data: String,
    var fkServico: UUID,
    var fkEmpresa: UUID,
    var nomeEmpresa: String,
    var nomeServico: String,
)
