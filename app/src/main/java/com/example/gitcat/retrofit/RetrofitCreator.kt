package com.example.gitcat.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreator {
        val API_BASE_URL = "https://a.gitcat.app/api/"

        private val defaultRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    val service: GithubAPI = defaultRetrofit.create(GithubAPI::class.java)

}