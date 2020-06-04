package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class LogoutModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Boolean
)