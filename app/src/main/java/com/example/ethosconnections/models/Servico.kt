package com.example.ethosconnections.models

import java.util.UUID

data class Servico(
    var id: UUID,
    var nomeServico: String ,
    var descricao: String ,
    var valor: Double,
    var areaAtuacaoEsg: String,
    var fkPrestadoraServico: UUID,
    var idEmpresa: UUID ,
    var nomeEmpresa:String
)
