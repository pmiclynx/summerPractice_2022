package com.summer.practice.tvtracker.favorites

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.practice.tvtracker.details.DetailsActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FavoritesFragment: Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list=generateDummyData()

        binding.recyclerViewFavorites.layoutManager=LinearLayoutManager(context)
        binding.recyclerViewFavorites.adapter=
            FavoriteMovieAdapter(list,object :FavoriteMovieAdapter.ItemClickListener {
                override fun onItemClicked(id: Int) {
                    startActivity(Intent(context, DetailsActivity::class.java))
                }
            })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateDummyData(): List<FavoriteMovie> {
        val list= mutableListOf<FavoriteMovie>()

        for (i in 0..10){
            list.add(
                FavoriteMovie(
                    id =i,
                    title ="Title $i",
                    imageUrl ="https://image.tmdb.org/t/p/w500/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg?api_key=563e3b53b3c3a7da6ceae87959d74162",
                    dateAdded = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")).toString()
                )
            )
        }
        return list

    }
}