package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

class TodayCommitModel {
    @SerializedName("message")
    val message: String = ""

    @SerializedName("data")
    val data: List<TodayCommitCountModel> = listOf()
}