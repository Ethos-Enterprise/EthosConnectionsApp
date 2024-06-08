package com.example.ethosconnections.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit service
object Api {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.15.8:8081")
//            .baseUrl("https://ethos-connections.ddns.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}