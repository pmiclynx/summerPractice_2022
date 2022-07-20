package com.summer.practice.tvtracker.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.summer.practice.tvtracker.databinding.ItemMovieBinding

class MovieAdapter(
    private val list: List<Movie>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val items: MutableList<Movie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    interface ItemDeleteListener {
        fun onItemDeleted(movie: Movie)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            itemClickListener: ItemClickListener,
            itemDeleteListener: ItemDeleteListener
        ) {
            binding.textViewMovieTitle.text = movie.title

            Glide.with(binding.root)
                .load(movie.imageUrl)
                .into(binding.imageViewMovie)

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    itemClickListener.onItemClicked(movie.id)
                }
            })

            binding.textViewDelete.setOnClickListener {
                itemDeleteListener.onItemDeleted(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val pos = position

        holder.bind(items[position], itemClickListener, object : ItemDeleteListener {
            override fun onItemDeleted(movie: Movie) {
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