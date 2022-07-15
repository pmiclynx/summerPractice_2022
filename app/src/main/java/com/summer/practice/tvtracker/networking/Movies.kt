package com.summer.practice.tvtracker.networking

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("backdrop_path") val backdropPath: String? = null,
)
