package com.summer.practice.tvtracker.data

import com.summer.practice.tvtracker.domain.MovieRepository

object MovieRepositoryFactory {

    val repository: MovieRepository by lazy {
        ImplMovieRepository(TheMovieDbDataSource(), FirebaseFavoriteMovieDataSource())
    }
}