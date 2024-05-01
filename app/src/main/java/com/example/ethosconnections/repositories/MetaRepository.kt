package com.example.ethosconnections.repositories

import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.service.MetaService
import retrofit2.Response
import java.util.UUID

class MetaRepository constructor(private val service: MetaService){
    suspend fun getAllMetas(): Response<List<Meta>> {
        return service.getAllMetas()
    }

    suspend fun getMetaById(id: UUID) = service.getMetaById(id)

    suspend fun postMeta(meta: Meta) = service.postMeta(meta)
}