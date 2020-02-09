package com.example.gitcat.retrofit

import com.example.gitcat.model.TodayCommitModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

class GithubAPI {
    interface GithubApiImpl {

        /*오늘의 커밋수*/
        @GET("/firstapi/todayCommitCount")
        fun getRepoList(@Query("email") email: String): Observable<TodayCommitModel>

    }

    companion object {
        fun getRepoList(email: String): Observable<TodayCommitModel> {
            return RetrofitCreator.create(GithubApiImpl::class.java).getRepoList(email)
        }

    }
}