package com.example.ethosconnections.repositories

import com.example.ethosconnections.service.InteracaoService
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import retrofit2.http.Query
import java.util.UUID

class InteracaoRepository constructor(private val service: InteracaoService) {

     suspend fun postInteracao(
          interacaoRequest: InteracaoViewModel.InteracaoRequest,
         token : String
    ) = service.postInteracao(interacaoRequest,token)

    suspend fun getInteracoesByFkEmpresa(fkEmpresa: UUID, token : String) = service.getInteracoesByFkEmpresa(fkEmpresa,token)

    suspend fun getInteracoesByServico(fkPrestadora: UUID, token : String) = service.getInteracoesByServico(fkPrestadora,token)

}