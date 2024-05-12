package com.example.searchimage

import com.example.searchimage.DTO.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("search/image")
    fun getData(
        @Header("Authorization") key: String,
        @Query("query") query: String
    ): Call<ResponseData>
}