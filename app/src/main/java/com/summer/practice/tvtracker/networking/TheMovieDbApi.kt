package com.summer.practice.tvtracker.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApi {
    @GET("tv")
    fun getList(@Query("page") page: Int = 1) : Call<Results>
}