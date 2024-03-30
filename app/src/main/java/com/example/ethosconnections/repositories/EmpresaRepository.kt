package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.EmpresaService

class EmpresaRepository constructor(private val  service:EmpresaService) {

    //fun loginEmpresa(email:String , senha:String) = service.loginEmpresa(email, senha)

    //TESTE MOCKAPI
    fun loginEmpresa() = service.loginEmpresa()
}