package com.example.testrootstack.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetroBase  {

    val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}