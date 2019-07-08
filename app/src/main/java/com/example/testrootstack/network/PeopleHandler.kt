package com.example.testrootstack.network

import com.example.testrootstack.models.PeopleBean
import retrofit2.Callback

class PeopleHandler {

    val retrofit = RetroBase.retrofit!!.create(PeopleService::class.java)

    fun getPeople(page : Int, callback : Callback<PeopleBean>){
        retrofit.getPeople(page).enqueue(callback)
    }
}