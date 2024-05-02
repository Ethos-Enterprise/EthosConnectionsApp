package com.example.ethosconnections.models

import java.util.UUID

data class Prestadora(
    var id:UUID? = null,
    var statusAprovacao:String? = null,
    var fkEmpresa:UUID? = null
)
