package com.summer.practice.tvtracker.favorites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.summer.practice.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.practice.tvtracker.details.DetailsActivity
import com.summer.practice.tvtracker.networking.makeToast

class FavoritesFragment: Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

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

        getData {
            binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewFavorites.adapter =
                FavoriteMovieAdapter(it, object : FavoriteMovieAdapter.ItemClickListener {
                    override fun onItemClicked(id: Int) {
                        val intent = Intent(context, DetailsActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                })
        }
    }

    private fun getData(
        onSuccess: (List<FavoriteMovie>) -> Unit
    ) {
        val list= mutableListOf<FavoriteMovie>()
        db.collection("users")
            .document(auth.uid.toString())
            .collection("favorites")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(
                        FavoriteMovie(
                            id = Integer.parseInt(document.data["id"] as String),
                            title = document.data["title"] as String,
                            dateAdded = document.data["dateTimeStamp"] as String,
                            imageUrl = document.data["backdropUrl"] as String
                        )
                    )
                }
                onSuccess.invoke(list)
            }
            .addOnFailureListener {
                makeToast(requireActivity(), "Error getting documents.")
            }
    }
}