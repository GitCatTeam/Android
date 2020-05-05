package com.example.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthCommitCountModel (
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MonthCommitCountModel_commits
)

data class MonthCommitCountModel_commits (
    @SerializedName("commits")
    val commits: MonthCommitCountModel_level

//    @SerializedName("detailCommits")
//    val detailCommits: map...?
)

data class MonthCommitCountModel_level(
    @SerializedName("level_1")
    val data1: ArrayList<String>,

    @SerializedName("level_2")
    val data2: ArrayList<String>,

    @SerializedName("level_3")
    val data3: ArrayList<String>
)
