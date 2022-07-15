package com.summer.practice.tvtracker.networking

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun getPopularList(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: (String) -> Unit
        ) {
        api.getPopularList(page).enqueue(object: Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (!response.isSuccessful) {
                    onError.invoke("Unsuccessful Api call")
                    return
                }

                response.body()?.let {
                    onSuccess.invoke(it.movies)
                    return
                }

                onError.invoke("Empty response body")
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                onError.invoke(t.toString())
            }
        })
    }

    fun getTopRatedList(
        page: Int = 1,
        onSuccess: (movies: List<Movies>) -> Unit,
        onError: (String) -> Unit
    ) {
        api.getTopRatedList(page).enqueue(object: Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if (!response.isSuccessful) {
                    onError.invoke("Unsuccessful Api call")
                    return
                }

                response.body()?.let {
                    onSuccess.invoke(it.movies)
                    return
                }

                onError.invoke("Empty response body")
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                onError.invoke(t.toString())
            }
        })
    }
}