package com.summer.practice.tvtracker.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepository(interceptor: Interceptor) {
    private val theMovieDbApi: TheMovieDbApi

    init {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        theMovieDbApi = retrofit.create(TheMovieDbApi::class.java)
    }

    fun getList(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = createCallBackObject(onSuccess, onError)
        theMovieDbApi.getList(page).enqueue(callback)
    }
}