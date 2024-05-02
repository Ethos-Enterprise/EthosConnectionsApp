package com.example.ethosconnections.models

import java.util.UUID

data class Empresa(

    //MUDANOD ID PRA STRING POR CONTA DO MOCKAPI
    var id: UUID? = null,
    var razaoSocial:String? = null,
    var cnpj:String? = null,
    var telefone:String? = null,
    var email:String? = null,
    var setor:String? = null,
    var qtdFuncionarios:Int? = null,
    var assinanteNewsletter:Boolean? = null,
    var plano:String? = null
)
