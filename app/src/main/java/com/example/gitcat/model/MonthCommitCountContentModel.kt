package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthCommitCountContentModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MonthCommitCountContentModel_data
)

data class MonthCommitCountContentModel_data(
    @SerializedName("result")
    val result: MonthCommitCountContentModel_result
)

data class MonthCommitCountContentModel_result(
    @SerializedName("repoName")
    val repoName: String,

    @SerializedName("commit")
    val commit: MonthCommitCountContentModel_commit
)

data class MonthCommitCountContentModel_commit(
    @SerializedName("time")
    val time: String,

    @SerializedName("message")
    val message: String
)