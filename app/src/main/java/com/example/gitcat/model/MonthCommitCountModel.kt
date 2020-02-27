package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthCommitCountModel (
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<MonthCommitCountModel_commits>
)

data class MonthCommitCountModel_commits (
    @SerializedName("level_1")
    val data1: List<String>,

    @SerializedName("level_2")
    val data2: List<String>,

    @SerializedName("level_3")
    val data3: List<String>
)
