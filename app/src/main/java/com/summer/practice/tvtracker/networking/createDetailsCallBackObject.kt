package com.summer.practice.tvtracker.networking

import com.summer.practice.tvtracker.details.Detail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun createDetailsCallBackObject(
    onSuccess: (detail: Detail) -> Unit,
    onError: (String) -> Unit
) = object: Callback<Detail> {
    override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
        if (!response.isSuccessful) {
            onError.invoke("Unsuccessful Api call")
            return
        }

        response.body()?.let {
            onSuccess.invoke(it)
            return
        }

        onError.invoke("Empty response body")
    }

    override fun onFailure(call: Call<Detail>, t: Throwable) {
        onError.invoke(t.toString())
    }
}