package com.summer.practice.tvtracker.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val baseUrl = "https://api.themoviedb.org/3/"
const val apiKey = "0a416fc6c49f4a04db6e3bd398ef8579"

interface Api {
    @GET("tv/popular")
    fun getPopularList(
        @Query("page") page: Int = 1,
        @Query("api_key") key: String = apiKey
    ) : Call<Results>

    @GET("tv/top_rated")
    fun getTopRatedList(
        @Query("page") page: Int = 1,
        @Query("api_key") key: String = apiKey
    ) : Call<Results>
}