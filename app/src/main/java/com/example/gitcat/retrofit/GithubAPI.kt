package com.example.gitcat.retrofit

import com.example.gitcat.model.MonthCommitCountModel
import com.example.gitcat.model.TodayCommitModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {

    /*오늘의 커밋수*/
    @GET("/firstapi/todayCommitCount")
    fun getRepoList(@Query("email") email: String): Call<TodayCommitModel>

    /*달력잔디_일별커밋수*/
    @GET("/calender/monthCommitCount")
    fun getMonthCommitCount(@Query("month") month: String): Call<MonthCommitCountModel>

}