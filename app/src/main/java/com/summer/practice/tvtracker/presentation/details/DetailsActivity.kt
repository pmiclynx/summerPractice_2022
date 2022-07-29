package com.summer.practice.tvtracker.presentation.details

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.data.MovieRepositoryFactory
import com.summer.practice.tvtracker.data.networking.createPathList
import com.summer.practice.tvtracker.data.networking.makeToast
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding
import com.summer.practice.tvtracker.domain.Detail
import com.summer.practice.tvtracker.domain.FavoriteMovie


class DetailsActivity : AppCompatActivity() {
    private lateinit var backDropUrl: String
    private lateinit var binding: ActivityDetailsBinding
    private var id: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        id = intent.getIntExtra("id", 0)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addToFavoritesButton.setOnClickListener {
            val favoriteMovie = FavoriteMovie(
                id = id,
                title = binding.title.text.toString(),
                imageUrl = backDropUrl,
                dateAdded = ""
            )

            MovieRepositoryFactory.repository.addFavorite(
                favoriteMovie = favoriteMovie,
                onSuccess = {
                    disableAddToFavoritesButton()
                    makeToast(this, it)
                },
                onError = { makeToast(this, it) }
            )
        }

        binding.backArrow.setOnClickListener {
            super.onBackPressed()
        }

        MovieRepositoryFactory.repository.getDetail(
            id = id,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )
    }

    private fun onError(error: String) {
        makeToast(this, error)
    }

    private fun onPopularMoviesFetched(detail: Detail) {
        val pathList = createPathList(detail)
        binding.title.text = pathList.name
        binding.rating.text = pathList.voteAverage.toString()
        binding.innerTextView.text = pathList.overview

        backDropUrl = pathList.backdropPath.toString()

        Glide.with(binding.root)
            .load(pathList.backdropPath)
            .transform(CenterCrop())
            .into(binding.backdropImage)

        Glide.with(this)
            .load(pathList.posterPath)
            .transform(CenterCrop())
            .into(binding.coverImage)

        disableButtonIfAlreadyFavorite()
    }

    private fun disableButtonIfAlreadyFavorite() {
        MovieRepositoryFactory.repository.findFavorite(
            id = id,
            onFound = {
                disableAddToFavoritesButton()
            }
        )
    }

    private fun disableAddToFavoritesButton() {
        binding.addToFavoritesButton.isEnabled = false
    }
}