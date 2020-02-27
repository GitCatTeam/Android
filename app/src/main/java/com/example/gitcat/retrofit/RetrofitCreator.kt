package com.example.gitcat.retrofit

import com.example.gitcat.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreator {
        val API_BASE_URL = "https://a.gitcat.app/api/"

        private val defaultRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    val service: GithubAPI = defaultRetrofit.create(GithubAPI::class.java)

}