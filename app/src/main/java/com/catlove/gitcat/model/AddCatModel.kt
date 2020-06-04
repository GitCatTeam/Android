package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class AddCatModel(
    @SerializedName("catId")
    val catId: Int,

    @SerializedName("catName")
    val catName: String
)