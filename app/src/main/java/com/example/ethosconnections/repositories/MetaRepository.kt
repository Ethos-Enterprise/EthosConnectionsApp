package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.service.MetaService
import retrofit2.Response
import java.util.UUID

class MetaRepository constructor(private val service: MetaService){
    suspend fun getAllMetas(token : String): Response<List<Meta>> {
        return service.getAllMetas(token)
    }
    suspend fun getMetasByFkEmpresa(fkEmpresa: UUID,token : String): Response<List<Meta>> {
        return service.getMetasByFkEmpresa(fkEmpresa, token)
    }
    suspend fun getMetaById(id: UUID, token : String) = service.getMetaById(id,token)

    suspend fun postMeta(meta: Meta, token : String) = service.postMeta(meta,token )

    suspend fun deleteMeta(id: UUID, token : String) = service.deleteMeta(id, token)

}