package com.example.gitcat.retrofit

import com.example.gitcat.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubAPI {

    /*오늘의 커밋수*/
    @GET("firstapi/todayCommitCount")
    fun getRepoList(
        @Query("email") email: String
    ): Call<TodayCommitModel>

    /*달력잔디_일별커밋수*/
    @GET("calender/commit-count")
    fun getMonthCommitCount(
        @Header("Authorization") token: String,
        @Query("date") date: String
    ): Call<MonthCommitCountModel>

    /*달력잔디_일별 커밋 내역*/
    @GET("calender/commit")
    fun getMonthCommitContent(
        @Header("Authorization") token: String,
        @Query("date") date: String
    ): Call<MonthCommitContentModel>

    /*월간레포트_목록*/
    @GET("report/list")
    fun getMonthlyList(
        @Header("Authorization") token: String
    ): Call<MonthlyListModel>

    /*월간레포트_상세*/
    @GET("report/detail")
    fun getMonthlyDetail(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ):Call<MonthlyDetailModel>

    /*고양이 선택*/
    @GET("home/cats")
    fun getCats(
        @Header("Authorization") token: String
    ): Call<DataModel>

    /*냥콜렉션*/
    @GET("collection/list")
    fun getCatsCollection(
        @Header("Authorization") token: String
    ): Call<CatsCollectionModel>
}