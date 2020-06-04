package com.catlove.gitcat.model

import com.google.gson.annotations.SerializedName

data class MonthlyDetailModel(
    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MonthlyDetailModel_data
)

data class MonthlyDetailModel_data(
    @SerializedName("comparedLastMonth")
    val comparedLastMonth: String,

    @SerializedName("avgCount")
    val avgCount: String,

    @SerializedName("dailyCount")
    val dailyCount: MonthlyDetailModel_dailyCount,

    @SerializedName("languageRatio")
    val languageRatio: MonthlyDetailModel_languageRatio,

    @SerializedName("contributedRepository")
    val contributedRepository: MonthlyDetailModel_contributedRepository,

    @SerializedName("comment")
    val comment: ArrayList<String>
)

data class MonthlyDetailModel_dailyCount(
    @SerializedName("dayArray")
    val dayArray: ArrayList<String>,

    @SerializedName("countArray")
    val countArray: ArrayList<Int>
)

data class MonthlyDetailModel_languageRatio(
    @SerializedName("percentArray")
    val percentArray: ArrayList<Float>,

    @SerializedName("resultLanguages")
    val resultLanguages: List<MonthlyDetailModel_resultLanguages>
)

data class MonthlyDetailModel_resultLanguages(
    @SerializedName("percent")
    val percent: Float,

    @SerializedName("language")
    val language: String
)

data class MonthlyDetailModel_contributedRepository(
    @SerializedName("count")
    val count: ArrayList<Float>,

    @SerializedName("repoNames")
    val repoNames: ArrayList<String>
)