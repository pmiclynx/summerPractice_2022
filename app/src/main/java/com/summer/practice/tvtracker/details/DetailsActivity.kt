package com.summer.practice.tvtracker.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding
import com.summer.practice.tvtracker.networking.*


class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
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

        MoviesDetailRepository(id).getDetail(
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

        Glide.with(binding.root)
            .load(pathList.backdropPath)
            .transform(CenterCrop())
            .into(binding.backdropImage)

        Glide.with(this)
            .load(pathList.posterPath)
            .transform(CenterCrop())
            .into(binding.coverImage)
    }
}