package com.example.ethosconnections.models

import java.util.UUID

data class Prestadora(
    var idPrestadora:UUID? = null,
    var statusAprovacao:String? = null,
    var fkEmpresa:UUID? = null
)

data class PrestadoraNova(
    var idEmpresa:UUID,
    var statusAprovacao:String,
)