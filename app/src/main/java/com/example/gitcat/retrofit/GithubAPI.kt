package com.example.gitcat.retrofit

import com.example.gitcat.model.MonthCommitCountModel
import com.example.gitcat.model.TodayCommitModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

class GithubAPI {
    interface GithubApiImpl {

        /*오늘의 커밋수*/
        @GET("/firstapi/todayCommitCount")
        fun getRepoList(@Query("email") email: String): Observable<TodayCommitModel>

        /*달력잔디_일별커밋수*/
        @GET("/calender/monthCommitCount")
        fun getMonthCommitCount(@Query("month") month: String): Observable<MonthCommitCountModel>
    }

    companion object {
        fun getRepoList(email: String): Observable<TodayCommitModel> {
            return RetrofitCreator.create(GithubApiImpl::class.java).getRepoList(email)
        }

        fun getMonthCommitCount(month: String): Observable<MonthCommitCountModel>{
            return RetrofitCreator.create(GithubApiImpl::class.java).getMonthCommitCount(month)
        }

    }
}