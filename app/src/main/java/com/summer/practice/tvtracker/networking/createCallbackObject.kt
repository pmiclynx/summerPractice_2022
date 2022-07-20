package com.summer.practice.tvtracker.networking

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    fun createCallBackObject(
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) = object: Callback<Results> {
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
    }