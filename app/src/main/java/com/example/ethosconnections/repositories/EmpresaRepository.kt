package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.service.EmpresaService
import retrofit2.Response
import retrofit2.http.POST
import java.util.UUID

class EmpresaRepository constructor(private val service:EmpresaService) {
    suspend fun loginEmpresa(email:String , senha:String) = service.loginEmpresa(email, senha)

    suspend fun getEmpresaPorId(id:UUID) = service.getEmpresPorId(id)


    suspend fun cadastrarEmpresa(
        nomeEmpresa: String,
        cnpj: String,
        telefone: String,
        email: String,
        senha: String,
        setor: String,
        qtdFuncionarios: Int,
        assinanteNewsletter: Boolean
    ) = service.cadastrarEmpresa(nomeEmpresa,cnpj,telefone,email,senha,setor,qtdFuncionarios,assinanteNewsletter)
}