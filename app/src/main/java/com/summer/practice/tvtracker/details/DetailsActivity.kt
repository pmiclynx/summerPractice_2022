package com.summer.practice.tvtracker.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding
import com.summer.practice.tvtracker.networking.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addToFavoritesButton.setOnClickListener {

            //TODO add 'save as favorite' logic

            val text = "Saved to favorites"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        binding.backArrow.setOnClickListener {
            super.onBackPressed()
        }

        MoviesDetailRepository(66732).getDetail(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError,
        )
    }

    private fun onError(error: String) {
        makeToast(this, error)
    }

    private fun onPopularMoviesFetched(detail: Detail) {
        val pathList = createPathList(detail)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        binding.title.text = pathList.name
        binding.rating.text = pathList.voteAverage.toString()
        binding.innerTextView.text = pathList.overview

        Glide.with(binding.root)
            .load(pathList.backdropPath)
            .transform(CenterCrop())
            .into(binding.backdropImage)

        Glide.with(binding.root)
            .load(pathList.posterPath)
            .transform(CenterCrop())
            .into(binding.coverImage)

        setContentView(binding.root)
    }
}