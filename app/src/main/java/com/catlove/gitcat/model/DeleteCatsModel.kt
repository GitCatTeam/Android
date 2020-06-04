package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class DeleteCatsModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Boolean
)