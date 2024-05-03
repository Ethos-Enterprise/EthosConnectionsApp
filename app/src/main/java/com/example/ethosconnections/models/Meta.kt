package com.example.ethosconnections.models

import java.time.LocalDate
import java.util.UUID

data class Meta (
    var uuid:UUID? = null,
    var pilarEsg:String? = null,
    var descricao:String? = null,
    var dataInicio:String? = null,
    var dataFim:String? = null
)