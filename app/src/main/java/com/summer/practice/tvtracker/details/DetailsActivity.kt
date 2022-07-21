package com.summer.practice.tvtracker.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding
import com.summer.practice.tvtracker.networking.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    private lateinit var backDropUrl: String
    private lateinit var posterUrl: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val id = intent.getIntExtra("id", 0)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addToFavoritesButton.setOnClickListener {

            val favorite = hashMapOf(
                "id" to id.toString(),
                "title" to binding.title.text,
                "backdropUrl" to backDropUrl,
                "posterUrl" to posterUrl,
                "dateTimeStamp" to LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")).toString()
            )

            auth.uid?.let { it1 ->
                db.collection("users")
                    .document(it1)
                    .collection("favorites")
                    .add(favorite)
                    .addOnSuccessListener {
                        Log.d("TAG", "DocumentSnapshot added with ID:")
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                    }
            }

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

        backDropUrl = pathList.backdropPath.toString()
        posterUrl = pathList.posterPath.toString()

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