package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.InteracaoService
import java.util.UUID

class InteracaoRepository constructor(private val service: InteracaoService) {

     suspend fun postInteracao(
         status: String,
         fkServico: UUID,
         fkEmpresa: UUID,
         token : String
    ) = service.postInteracao(status, fkServico, fkEmpresa,token)

    suspend fun getInteracoesByFkEmpresa(fkEmpresa: UUID, token : String) = service.getInteracoesByFkEmpresa(fkEmpresa,token)

    suspend fun getInteracoesByServico(fkPrestadora: UUID, token : String) = service.getInteracoesByServico(fkPrestadora,token)

}