package com.example.ethosconnections.models

import java.util.UUID

data class Empresa(

    var id: UUID? = null,
    var razaoSocial:String? = null,
    var cnpj:String? = null,
    var telefone:String? = null,
    var email:String? = null,
    var setor:String? = null,
    var qtdFuncionarios:Int? = null,
    var assinanteNewsletter:Boolean? = null,
    var plano:String? = null,
    var idPrestadora:UUID? = null

)

data class EmpresaNova(
    val nomeEmpresa: String,
    val cnpj: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val setor: String,
    val qtdFuncionarios: Int,
    val assinanteNewsletter: Boolean
)