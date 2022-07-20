package com.summer.practice.tvtracker.details

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding
import com.summer.practice.tvtracker.networking.*


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

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
            Log.d("??????????????????", "????????????????????")
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
        val title = findViewById<TextView>(R.id.title)
        title.text = pathList.name
        val rating = findViewById<TextView>(R.id.rating)
        rating.text = pathList.voteAverage.toString()
        val overview = findViewById<TextView>(R.id.innerTextView)
        overview.text = pathList.overview

        Glide.with(this)
            .load(pathList.backdropPath)
            .transform(CenterCrop())
            .into(findViewById(R.id.backdropImage))

        Glide.with(this)
            .load(pathList.posterPath)
            .transform(CenterCrop())
            .into(findViewById(R.id.coverImage))
    }
}