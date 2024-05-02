package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.EmpresaService
import java.util.UUID

class EmpresaRepository constructor(private val service:EmpresaService) {
    suspend fun loginEmpresa(email:String , senha:String) = service.loginEmpresa(email, senha)

    suspend fun getEmpresaPorId(id:UUID) = service.getEmpresPorId(id)

}