package com.summer.practice.tvtracker.networking

fun createPathList(movies: List<Movie>): List<Movie> {
    val pathList = movies.map {
        it.copy(backdropPath = imageBaseUrl + it.backdropPath)
    }
    //TODO if backdropPath is null we should load a placeholder
    return pathList
}