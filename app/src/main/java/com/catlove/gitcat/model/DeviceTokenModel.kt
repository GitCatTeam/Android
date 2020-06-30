package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class DeviceTokenModel (
    @SerializedName("deviceToken")
    val deviceToken: String,

    @SerializedName("deviceId")
    val deviceId: String
)