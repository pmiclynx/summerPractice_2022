package com.summer.practice.tvtracker.domain

interface FavoriteMovieDataSource {
    fun getFavorites(
        onSuccess: (movies: List<FavoriteMovie>) -> Unit,
        onError: (String) -> Unit
    )

    fun deleteFavorite(id: String)

    fun findFavorite(
        id: Int,
        onFound: (String) -> Unit
    )

    fun addFavorite(
        favoriteMovie: FavoriteMovie,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )
}