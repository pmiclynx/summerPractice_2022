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

class TopRatedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MoviesRepository.getTopRatedList(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
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