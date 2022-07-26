package com.summer.practice.tvtracker.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.summer.practice.tvtracker.R
import com.summer.practice.tvtracker.databinding.ItemFavoriteMovieBinding
import com.summer.practice.tvtracker.databinding.LastItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    private val list: List<FavoriteMovie>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<FavoriteMovieAdapter.GenericViewHolder>() {

    private val items: MutableList<FavoriteMovie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    interface ItemDeleteListener {
        fun onItemDeleted(movie: FavoriteMovie)
    }

    abstract class GenericViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        abstract fun bind(movie: FavoriteMovie, itemClickListener: ItemClickListener,itemDeleteListener: ItemDeleteListener)

    }

    class MovieViewHolder(view: View, private val binding: ItemFavoriteMovieBinding) :
        GenericViewHolder(binding.root) {

        override fun bind(
            movie: FavoriteMovie,
            itemClickListener: ItemClickListener,
            itemDeleteListener: ItemDeleteListener
        ) {
            binding.textViewMovieTitle.text = movie.title

            Glide.with(binding.root).load(movie.imageUrl).into(binding.imageViewMovie)

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    itemClickListener.onItemClicked(movie.id)
                }
            })

            binding.textViewDelete.setOnClickListener {
                itemDeleteListener.onItemDeleted(movie)
            }

            binding.textViewDate.text=movie.dateAdded.toString()
        }

    }
    class LastMovieViewHolder(view: View, private val binding: LastItemFavoriteMovieBinding) :
        GenericViewHolder(binding.root) {
        override fun bind(
            movie: FavoriteMovie,
            itemClickListener: ItemClickListener,
            itemDeleteListener: ItemDeleteListener
        ) {
            binding.textViewMovieTitle.text = movie.title

            Glide.with(binding.root).load(movie.imageUrl).into(binding.imageViewMovie)

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    itemClickListener.onItemClicked(movie.id)
                }
            })

            binding.textViewDelete.setOnClickListener {
                itemDeleteListener.onItemDeleted(movie)
            }

            binding.textViewDate.text = movie.dateAdded.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        if(viewType == items.size-1){
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.last_item_favorite_movie, parent, false)
            return LastMovieViewHolder(view,LastItemFavoriteMovieBinding.bind(view))
        }
        else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_movie, parent, false)
            return MovieViewHolder(view, ItemFavoriteMovieBinding.bind(view))
        }

    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
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