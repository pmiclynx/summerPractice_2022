package com.summer.practice.tvtracker.topRated

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.FragmentTopRatedBinding
import com.summer.practice.tvtracker.details.DetailsActivity
import com.summer.practice.tvtracker.popular.Adapter



class TopRatedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentTopRatedBinding = FragmentTopRatedBinding.inflate(inflater,container,false)

        val exampleList = generateDummyList(50)

        binding.recyclerViewTopRated.adapter = Adapter(
            exampleList,
            object: Adapter.ItemClickListener {
            override fun onItemClicked(id: Int) {
                startActivity(Intent(context, DetailsActivity::class.java))
            }

        })
        binding.recyclerViewTopRated.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTopRated.setHasFixedSize(true)

        return binding.root
    }

    private fun generateDummyList(size: Int): List<Movie> {

        val list = ArrayList<Movie>()

        for (i in 0 until size) {
            val drawable = R.drawable.ic_baseline_local_movies_24

            val item = Movie(
                id = i,
                title = "Top Rated $i",
                imageUrl = "https://image.tmdb.org/t/p/w500/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg?api_key=563e3b53b3c3a7da6ceae87959d74162"
            )
            list += item
        }

        return list
    }
}