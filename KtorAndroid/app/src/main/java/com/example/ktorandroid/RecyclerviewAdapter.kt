package com.example.ktorandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_movies.view.*

class RecyclerviewAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movies, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.name
        Glide.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.image)
        holder.desc.text = movie.desc
        holder.category.text = movie.category
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieItems(movieList: List<Movie>) {
        this.movieList = movieList.toMutableList()
        notifyDataSetChanged()
    }


}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image = view.imgImage
    val title = view.textTitle
    val desc = view.textDesc
    val category = view.buttonCategory
}
