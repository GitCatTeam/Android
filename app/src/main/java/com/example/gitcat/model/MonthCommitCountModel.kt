package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

class MonthCommitCountModel {
    @SerializedName("message")
    val message: String = ""

    @SerializedName("data")
    val data: List<MonthCommitCountModel_commits> = listOf()
}