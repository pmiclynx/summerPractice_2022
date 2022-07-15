package com.summer.practice.tvtracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.summer.practice.tvtracker.details.DetailsActivity

class Adaptor(private val exampleList: List<ExampleItem>, private val context: Context) : RecyclerView.Adapter<Adaptor.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)

        return ExampleViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.titleView.text = currentItem.text1
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(val itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewMovie)
        val titleView: TextView = itemView.findViewById(R.id.textViewMovieTitle)
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                context.startActivity(intent)
            }

        }
    }

    //Todo Click on item recylcerview andorid
}
