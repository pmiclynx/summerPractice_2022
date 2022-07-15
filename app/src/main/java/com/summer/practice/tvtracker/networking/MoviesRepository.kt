package com.summer.practice.tvtracker.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val theMovieDbApi: TheMovieDbApi

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        theMovieDbApi = retrofit.create(TheMovieDbApi::class.java)
    }

    fun getPopularList(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = CreateCallbackObject(onSuccess, onError).createObject()
        theMovieDbApi.getPopularList(page).enqueue(callback)
    }

    fun getTopRatedList(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = CreateCallbackObject(onSuccess, onError).createObject()
        theMovieDbApi.getTopRatedList(page).enqueue(callback)
    }
}