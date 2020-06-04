package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthlyListModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MonthlyListModel_resultList
)

data class MonthlyListModel_resultList(
    @SerializedName("resultList")
    val resultList: List<MonthlyListModel_data>
)

data class MonthlyListModel_data(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("totalCount")
    val totalCount: String,

    @SerializedName("mainLanguage")
    val mainLanguage: String
)