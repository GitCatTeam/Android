package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenModel(
    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: RefreshTokenModel_data
)

data class RefreshTokenModel_data(
    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("refreshToken")
    var refreshToken: String
)