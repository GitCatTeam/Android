package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthCommitContentModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MonthCommitCountContentModel_data
)

data class MonthCommitCountContentModel_data(
    @SerializedName("score")
    val score : String,

    @SerializedName("totalCommit")
    val totalCommit: String,

    @SerializedName("item")
    val item: String,

    @SerializedName("commits")
    val commits: List<MonthCommitCountContentModel_result>
)

data class MonthCommitCountContentModel_result(
    @SerializedName("repoName")
    val repoName: String,

    @SerializedName("commit")
    val commit: List<MonthCommitCountContentModel_commit>
)

data class MonthCommitCountContentModel_commit(
    @SerializedName("time")
    val time: String,

    @SerializedName("message")
    val message: String
)