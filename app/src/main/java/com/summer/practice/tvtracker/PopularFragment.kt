package com.summer.practice.tvtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.summer.practice.tvtracker.networking.Movies
import com.summer.practice.tvtracker.networking.MoviesRepository

class PopularFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        MoviesRepository.getPopularList(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    private fun onPopularMoviesFetched(movies: List<Movies>) {
        val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
        val pathList =  movies.map {
            it.copy(backdropPath = imageBaseUrl + it.backdropPath)
        }
        Log.d("movies:", movies[0].toString())
        Log.d("pathList:", pathList.toString())
    }

    private fun onError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }
}