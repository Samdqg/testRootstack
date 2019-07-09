package com.example.testrootstack.models

import com.google.gson.annotations.SerializedName

class PeopleBean{

    val results: List<People>? = null

    class People{
        @SerializedName("name")
        val name: String? = null

        @SerializedName("height")
        val mHeight: String? = null

        @SerializedName("mass")
        val mass: String? = null

        @SerializedName("hair_color")
        val hair_color: String? = null

        @SerializedName("skin_color")
        val skin_color: String? = null

        @SerializedName("eye_color")
        val eye_color: String? = null

        @SerializedName("gender")
        val gender : String? = null
    }

}

