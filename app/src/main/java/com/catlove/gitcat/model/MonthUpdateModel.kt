package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthUpdateModel(
    @SerializedName("date")
    val date: String
)