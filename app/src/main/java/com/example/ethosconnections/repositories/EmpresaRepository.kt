package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.EmpresaNova
import com.example.ethosconnections.service.EmpresaService
import retrofit2.Response
import retrofit2.http.POST
import java.util.UUID

class EmpresaRepository constructor(private val service:EmpresaService) {
    suspend fun loginEmpresa(email: String, senha: String, token: String): Response<Empresa> {
        return service.loginEmpresa(email, senha, token)
    }
    suspend fun getEmpresaPorId(id:UUID, token : String) = service.getEmpresPorId(id, token)


    suspend fun cadastrarEmpresa(
        empresaNova: EmpresaNova,
        token: String
    ) = service.cadastrarEmpresa(empresaNova ,token)
}