package com.example.ethosconnections.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit service
object Api {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            //colcoar o ip da maquina
            //.baseUrl("http://192.168.56.1:8081/")

            //TESTE MOCKAPI
            .baseUrl("https://6515fcef09e3260018c95361.mockapi.io/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}