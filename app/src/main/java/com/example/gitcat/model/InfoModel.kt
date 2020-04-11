package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class InfoModel(
    @SerializedName("name")
    val name: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("birth")
    val birth: String,

    @SerializedName("devCareer")
    val devCareer: String
)