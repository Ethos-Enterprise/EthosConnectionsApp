package com.example.ethosconnections.models

data class Portfolio (
    var uuid: String? = null,
    var urlImagemPerfil: String? = null,
    var urlBackgroundPerfil: String? = null,
    var descricaoEmpresa: String? = null,
    var sobreEmpresa: String? = null,
    var linkWebsiteEmpresa: String? = null,
    var dataEmpresaCertificada: String? = null,
    var fkPrestadoraServico: String? = null
)
data class Foto (
    val url: String,
    val altura: Int,
    val largura: Int
)