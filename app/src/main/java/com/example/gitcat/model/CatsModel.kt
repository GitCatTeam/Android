package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class CatsModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: CatsModel_data
)

data class CatsModel_data(
    @SerializedName("common")
    val common: List<CatsModel_common>
)

data class CatsModel_common(
    @SerializedName("id")
    val id: String,

    @SerializedName("profileImg")
    var profileImg: String,

    @SerializedName("cost")
    var cost: Int,

    @SerializedName("payType")
    val payType: String,

    @SerializedName("isAvailable")
    val isAvailable: Boolean
)

