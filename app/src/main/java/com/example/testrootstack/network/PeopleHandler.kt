package com.example.testrootstack.network

import com.example.testrootstack.models.PeopleBean
import retrofit2.Callback

class PeopleHandler : RetroBase() {

    val service = retrofit!!.create(PeopleService::class.java)

    fun getPeople(page : Int, callback : Callback<PeopleBean>){
        service.getPeople(page).enqueue(callback)
    }
}