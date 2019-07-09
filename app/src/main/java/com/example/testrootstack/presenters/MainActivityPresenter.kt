package com.example.testrootstack.presenters

import com.example.testrootstack.models.PeopleBean
import com.example.testrootstack.network.PeopleHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(val recycler : Recycler) {


    fun getPeople(page : Int){
        val peopleHandler = PeopleHandler()
        peopleHandler.getPeople(page, object : Callback<PeopleBean>{

            override fun onResponse(call: Call<PeopleBean>, response: Response<PeopleBean>) {
                if (response.isSuccessful){
                    val data = response.body()
                    recycler.populateRecylcer(ArrayList(data!!.results!!))
                }else{
                    recycler.showToast()
                }
            }

            override fun onFailure(call: Call<PeopleBean>, t: Throwable) {
                recycler.showToast()

            }

        })
    }
    interface Recycler{
        fun populateRecylcer(peopleList: ArrayList<PeopleBean.People>)
        fun showToast()
    }
}