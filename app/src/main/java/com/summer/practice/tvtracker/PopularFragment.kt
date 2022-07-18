package com.summer.practice.tvtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.practice.tvtracker.networking.*

class PopularFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MoviesRepository(PathInterceptor("popular")).getList(
            1,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        val pathList = createPathList(movies)
        Log.d("movies:", movies[0].toString())
        Log.d("pathList:", pathList.toString())
    }

    private fun onError(error: String) {
        makeToast(requireActivity(), error)
    }
}