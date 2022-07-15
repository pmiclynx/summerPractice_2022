package com.summer.practice.tvtracker.networking

fun createPathList(movies: List<Movies>): List<Movies> {
    val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
    val pathList = movies.map {
        it.copy(backdropPath = imageBaseUrl + it.backdropPath)
    }
    //TODO if backdropPath is null we should load a placeholder
    return pathList
}