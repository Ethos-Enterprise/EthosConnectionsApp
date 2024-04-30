package com.example.ethosconnections.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit service
object Api {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.18.35.87:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}