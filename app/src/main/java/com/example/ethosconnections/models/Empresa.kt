package com.example.ethosconnections.models

import java.util.UUID

data class Empresa(
    var id: UUID,
    var razaoSocial:String,
    var cnpj:String,
    var telefone:String,
    var email:String,
    var setor:String,
    var qtdFuncionarios:Int,
    var assinanteNewsletter:Boolean
)
