package com.summer.practice.tvtracker.networking

import com.summer.practice.tvtracker.details.Detail

fun createPathList(movies: List<Movie>): List<Movie> {
    val pathList = movies.map {
        var newPath: String? = null
        it.backdropPath?.let{
            newPath = imageBaseUrl + it
        }
        it.copy(backdropPath = newPath)
    }
    //TODO if backdropPath is null we should load a placeholder
    return pathList
}

fun createPathList(detail: Detail): Detail {
    var newBackdropPath: String? = null
    var newPosterPath: String? = null

    detail.backdropPath?.let{
        newBackdropPath = imageBaseUrl + detail.backdropPath
    }

    detail.posterPath?.let{
        newPosterPath = imageBaseUrl + detail.posterPath
    }

    return detail.copy(
        backdropPath = newBackdropPath,
        posterPath = newPosterPath
    )
}