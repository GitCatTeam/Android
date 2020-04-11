package com.example.gitcat.retrofit

import com.example.gitcat.model.*
import retrofit2.Call
import retrofit2.http.*

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

    /*부가 정보 입력*/
    @PUT("auth/additional")
    fun putInfo(
        @Header("Authorization") token: String
    ): Call<InfoModel>

    /*냥콜렉션*/
    @GET("collection/list")
    fun getCatsCollection(
        @Header("Authorization") token: String
    ): Call<CatsCollectionModel>

    /*로그아웃*/
    @POST("auth/logout")
    fun postLogout(
        @Header("Authorization") token: String
    ): Call<LogoutModel>

    /*고양이 초기화*/
    @DELETE("home/cats")
    fun deleteCats(
        @Header("Authorization") token: String
    ): Call<DeleteCatsModel>

    /*회원 탈퇴*/
    @DELETE("auth/withdraw")
    fun deleteWithdraw(
        @Header("Authorization") token: String
    ): Call<WithdrawModel>
}