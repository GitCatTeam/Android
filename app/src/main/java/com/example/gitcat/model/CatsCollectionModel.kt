package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class CatsCollectionModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<CatsCollectionModel_data>
)

data class CatsCollectionModel_data(
    @SerializedName("name")
    val name: String,

    @SerializedName("endingMent")
    val endingMent: String,

    @SerializedName("isMedal")
    val isMedal: Boolean,

    @SerializedName("img")
    val img: String
)