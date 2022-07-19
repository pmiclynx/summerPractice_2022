package com.summer.practice.tvtracker.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.FragmentPopularBinding
import com.summer.practice.tvtracker.details.DetailsActivity
import com.summer.practice.tvtracker.topRated.Movie


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPopular.adapter = MovieAdapter(
            generateDummyList(20),
            object: MovieAdapter.ItemClickListener {
                override fun onItemClicked(id: Int) {
                    startActivity(Intent(context, DetailsActivity::class.java))
                }

            })
    }

    private fun generateDummyList(size: Int): List<Movie> {

        val list = ArrayList<Movie>()

        for (i in 0 until size) {
            val drawable = R.drawable.ic_baseline_local_movies_24

            val item = Movie(
                id = i,
                title = "Title $i",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg?api_key=563e3b53b3c3a7da6ceae87959d74162"
            )
            list += item
        }

        return list
    }
}