package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.EmpresaService

class EmpresaRepository constructor(private val  service:EmpresaService) {

    fun loginEmpresa() = service.loginEmpresa("alecrim", "dourado")
}