package com.summer.practice.tvtracker.networking

import com.summer.practice.tvtracker.details.Detail
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesDetailRepository(id: Int) {
    private val theMovieDbApi: TheMovieDbApi

    init {
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(DetailInterceptor(id))
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        theMovieDbApi = retrofit.create(TheMovieDbApi::class.java)
    }

    fun getDetail(
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = createDetailsCallBackObject(onSuccess, onError)
        theMovieDbApi.getDetail().enqueue(callback)
    }}