package com.summer.practice.tvtracker.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.summer.practice.tvtracker.databinding.ActivityDetailsBinding

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
    }
}