package com.example.velmurugan.cardviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.velmurugan.cardviewexample.RecyclerViewAdapter.MyViewHolder

class RecyclerViewAdapter constructor(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MyViewHolder>() {
    private var clickListener: ClickListener<Movie>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_adapter_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.title
        holder.image.setBackgroundResource(movie.image)
        holder.cardView.setOnClickListener { clickListener!!.onItemClick(movie) }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setOnItemClickListener(movieClickListener: ClickListener<Movie>?) {
        clickListener = movieClickListener
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val cardView: CardView = itemView.findViewById(R.id.carView)

    }
}

interface ClickListener<T> {
    fun onItemClick(data: T)
}