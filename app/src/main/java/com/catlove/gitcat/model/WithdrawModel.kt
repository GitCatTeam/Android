package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class WithdrawModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Boolean
)