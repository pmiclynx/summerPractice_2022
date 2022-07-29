package com.summer.practice.tvtracker.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.summer.practice.tvtracker.domain.FavoriteMovie
import com.summer.practice.tvtracker.domain.FavoriteMovieDataSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FirebaseFavoriteMovieDataSource : FavoriteMovieDataSource {

    override fun getFavorites(
        onSuccess: (movies: List<FavoriteMovie>) -> Unit,
        onError: (String) -> Unit
    ) {
        getFavoritesCollection()
            .get()
            .addOnSuccessListener { result ->
                val list = result.map { document ->
                    FavoriteMovie(
                        id = Integer.parseInt(document.data["id"] as String),
                        title = document.data["title"] as String,
                        dateAdded = document.data["dateTimeStamp"] as String,
                        imageUrl = document.data["backdropUrl"] as String
                    )
                }

                onSuccess.invoke(list)
            }
            .addOnFailureListener {
                onError.invoke("Error getting documents.")
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addFavorite(
        favoriteMovie: FavoriteMovie,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val favorite = hashMapOf(
            "id" to favoriteMovie.id.toString(),
            "title" to favoriteMovie.title,
            "backdropUrl" to favoriteMovie.imageUrl,
            "dateTimeStamp" to LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                .toString()
        )

        getFavoritesCollection()
            .add(favorite)
            .addOnSuccessListener {
                onSuccess("Saved to favorites")
            }
            .addOnFailureListener {
                onError("Error adding document")
            }
    }

    override fun deleteFavorite(id: String) {
        findFavorite(id = id.toInt(),
            onFound = {
                getFavoritesCollection()
                    .document(it)
                    .delete()
            }
        )
    }

    override fun findFavorite(id: Int, onFound: (String) -> Unit) {
        getFavoritesCollection()
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (Integer.parseInt(document.data["id"] as String) == id) {
                        onFound.invoke(document.id)
                        break
                    }
                }
            }
    }

    private fun getFavoritesCollection(
    ): CollectionReference {
        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().uid.toString()
        return db.collection("users")
            .document(userId)
            .collection("favorites")
    }
}