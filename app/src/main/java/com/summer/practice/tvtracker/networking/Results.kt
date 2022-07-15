package com.summer.practice.tvtracker.networking

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results") var movies: List<Movies> = emptyList(),
)
