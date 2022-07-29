package com.summer.practice.tvtracker.data

import com.summer.practice.tvtracker.data.networking.imageBaseUrl
import com.summer.practice.tvtracker.domain.*

class ImplMovieRepository(
    private val movieDataSource: MovieDataSource,
    private val favoritesMovieDataSource: FavoriteMovieDataSource
) : MovieRepository {

    override fun getMovies(
        category: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: (String) -> Unit
    ) {
        movieDataSource.getMovies(
            category = category,
            onSuccess = { onSuccess.invoke(createPathList(it)) },
            onError = onError
        )
    }

    override fun getDetail(
        id: Int,
        onSuccess: (detail: Detail) -> Unit,
        onError: (String) -> Unit
    ) {
        movieDataSource.getDetail(id, onSuccess, onError)
    }

    override fun getFavorites(
        onSuccess: (movies: List<FavoriteMovie>) -> Unit,
        onError: (String) -> Unit
    ) {
        favoritesMovieDataSource.getFavorites(onSuccess, onError)
    }

    override fun addFavorite(
        favoriteMovie: FavoriteMovie,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        favoritesMovieDataSource.addFavorite(favoriteMovie, onSuccess, onError)
    }

    override fun deleteFavorite(id: String) {
        favoritesMovieDataSource.deleteFavorite(id)
    }

    override fun findFavorite(id: Int, onFound: (String) -> Unit) {
        favoritesMovieDataSource.findFavorite(id, onFound)
    }

    private fun createPathList(movies: List<Movie>): List<Movie> {
        val pathList = movies.map {
            var newPath: String? = null
            it.backdropPath?.let {
                newPath = imageBaseUrl + it
            }
            it.copy(backdropPath = newPath)
        }
        return pathList
    }
}
