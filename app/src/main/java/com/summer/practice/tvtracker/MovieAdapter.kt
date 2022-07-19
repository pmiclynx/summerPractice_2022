package com.summer.practice.tvtracker.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.summer.practice.tvtracker.databinding.ItemMovieSimpleBinding
import com.summer.practice.tvtracker.topRated.Movie

class MovieAdapter(
    private val list: List<Movie>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val items: MutableList<Movie> = list.toMutableList()

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    class MovieViewHolder(private val binding: ItemMovieSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            itemClickListener: ItemClickListener,
        ) {
            binding.textViewMovieTitle.text = movie.title

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    itemClickListener.onItemClicked(movie.id)

                }
            })
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