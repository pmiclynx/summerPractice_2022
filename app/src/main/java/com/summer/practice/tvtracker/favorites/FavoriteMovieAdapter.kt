package com.summer.practice.tvtracker.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.summer.practice.tvtracker.databinding.ItemFavoriteMovieBinding
import com.summer.practice.tvtracker.db.findInFavorites

class FavoriteMovieAdapter(
    list: List<FavoriteMovie>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder>() {

    private val items: MutableList<FavoriteMovie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    interface ItemDeleteListener {
        fun onItemDeleted(movie: FavoriteMovie)
    }

    class MovieViewHolder(private val binding: ItemFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: FavoriteMovie,
            itemClickListener: ItemClickListener,
            itemDeleteListener: ItemDeleteListener
        ) {
            val db = Firebase.firestore
            val auth = FirebaseAuth.getInstance()

            binding.textViewMovieTitle.text = movie.title

            Glide.with(binding.root).load(movie.imageUrl).into(binding.imageViewMovie)

            binding.root.setOnClickListener { itemClickListener.onItemClicked(movie.id) }

            fun deleteDocumentFromDataBase(documentID: String){
                db.collection("users")
                    .document(auth.uid.toString())
                    .collection("favorites")
                    .document(documentID)
                    .delete()
            }

            binding.textViewDelete.setOnClickListener {
                findInFavorites(
                    db = db,
                    userId = auth.uid.toString(),
                    idToFund = movie.id,
                    onFound = ::deleteDocumentFromDataBase
                )
                itemDeleteListener.onItemDeleted(movie)
            }

            binding.textViewDate.text = movie.dateAdded

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val pos = position

        holder.bind(items[position], itemClickListener, object : ItemDeleteListener {
            override fun onItemDeleted(movie: FavoriteMovie) {
                items.remove(movie)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, itemCount)
            }

        })

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
