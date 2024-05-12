package com.example.searchimage

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}