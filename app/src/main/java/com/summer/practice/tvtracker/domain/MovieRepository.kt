package com.summer.practice.tvtracker.domain

interface MovieRepository {

    /** Fetches the top rated & popular data. */
    fun getMovies(
        category: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    )

    fun getDetail(
        id: Int,
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    )

    fun getFavorites(
        onSuccess: (movies: List<FavoriteMovie>) -> Unit,
        onError: (String) -> Unit
    )

    fun addFavorite(
        favoriteMovie: FavoriteMovie,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun deleteFavorite(id: String)

    fun findFavorite(
        id: Int,
        onFound: (String) -> Unit
    )
}