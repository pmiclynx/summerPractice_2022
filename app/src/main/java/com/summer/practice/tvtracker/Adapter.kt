package com.summer.practice.tvtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.summer.practice.tvtracker.databinding.ItemMovieSimpleBinding
import com.summer.practice.tvtracker.networking.Movie

class Adapter(
    list: List<Movie>,
    private val itemClickListener: ItemClickListener
): RecyclerView.Adapter<Adapter.MovieViewHolder>() {

    private val items: MutableList<Movie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    class MovieViewHolder(private val binding: ItemMovieSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: Movie,
            itemClickListener: ItemClickListener,
        ) {
            binding.textViewMovieTitle.text = movie.name

            Glide.with(itemView)
                .load(movie.backdropPath)
                .transform(CenterCrop())
                .placeholder(R.drawable.series_image)
                .into(binding.imageViewMovie)
            binding.root.setOnClickListener { itemClickListener.onItemClicked(movie.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val popularItem = items[position]
        holder.bind(popularItem,itemClickListener)
    }
}