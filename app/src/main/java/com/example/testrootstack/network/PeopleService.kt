package com.example.testrootstack.network

import com.example.testrootstack.models.PeopleBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    @GET("people")
    fun getPeople(@Query("page") page: Int) : Call<PeopleBean>
}