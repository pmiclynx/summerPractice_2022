package com.summer.practice.tvtracker.favorites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.summer.practice.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.practice.tvtracker.details.DetailsActivity


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = generateDummyData()

        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFavorites.adapter =
            MovieAdapter(list, object : MovieAdapter.ItemClickListener {
                override fun onItemClicked(id: Int) {
                    startActivity(Intent(context, DetailsActivity::class.java))
                }
            })
    }

    fun generateDummyData(): List<Movie> {
        val list = mutableListOf<Movie>()

        for (i in 0..10) {
            list.add(
                Movie(
                    id = i,
                    title = "Title $i",
                    imageUrl = "https://image.tmdb.org/t/p/w500/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg?api_key=563e3b53b3c3a7da6ceae87959d74162"
                )
            )
        }

        return list
    }
}