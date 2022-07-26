package com.summer.practice.tvtracker.db

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun getFavoritesCollection(
): CollectionReference {
    val db = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().uid.toString()
    return db.collection("users")
        .document(userId)
        .collection("favorites")
}

fun findFavorites(
    id: Int,
    onFound: (String) -> Unit
) {
    getFavoritesCollection()
        .get()
        .addOnSuccessListener { result ->
            for (document in result){
                if(Integer.parseInt(document.data["id"] as String) == id){
                    onFound.invoke(document.id)
                    break
                }
            }
        }
}

fun deleteFromFavorites(id: String) {
    getFavoritesCollection()
        .document(id)
        .delete()
}