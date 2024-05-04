package com.example.ethosconnections.models

import java.time.LocalDate
import java.util.UUID

data class Avaliacao (
    var id: UUID? = null,
    var comentario:String? = null,
    var nota:Int? = null,
    var fkEmpresa:UUID? = null,
    var fkServico:UUID? = null,
    var data:LocalDate? = null
)