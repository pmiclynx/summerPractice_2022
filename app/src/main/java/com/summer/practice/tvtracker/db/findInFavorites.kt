package com.summer.practice.tvtracker.db

import com.google.firebase.firestore.FirebaseFirestore

fun findInFavorites(
    db: FirebaseFirestore,
    userId: String,
    idToFund: Int,
    onFound: (String) -> Unit
) {
    db.collection("users")
        .document(userId)
        .collection("favorites")
        .get()
        .addOnSuccessListener { result ->
            for (document in result){
                if(Integer.parseInt(document.data["id"] as String) == idToFund){
                    onFound.invoke(document.id)
                    break
                }
            }
        }
}