package com.summer.practice.tvtracker.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.Adapter
import com.summer.practice.tvtracker.databinding.FragmentPopularBinding
import com.summer.practice.tvtracker.details.DetailsActivity
import com.summer.practice.tvtracker.networking.MoviesRepository
import com.summer.practice.tvtracker.networking.PathInterceptor
import com.summer.practice.tvtracker.networking.createPathList
import com.summer.practice.tvtracker.networking.makeToast


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MoviesRepository(PathInterceptor("popular")).getList(
            1,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )

        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onPopularMoviesFetched(movies: List<com.summer.practice.tvtracker.networking.Movie>) {
        val pathList = createPathList(movies)
        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPopular.adapter = Adapter(
            pathList,
            object: Adapter.ItemClickListener {
                override fun onItemClicked(id: Int) {
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            })
    }

    private fun onError(error: String) {
        makeToast(requireActivity(), error)
    }
}