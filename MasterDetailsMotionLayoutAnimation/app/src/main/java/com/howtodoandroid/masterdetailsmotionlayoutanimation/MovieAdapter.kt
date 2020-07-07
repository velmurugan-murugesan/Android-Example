package com.howtodoandroid.masterdetailsmotionlayoutanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()
    var itemClickListener: ListClickListener<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.textViewTitle.text = movie.title

        Glide
            .with(holder.view.context)
            .load(movie.image)
            .centerCrop()
            .into(holder.imageviewMovie)

        holder.imageviewMovie.setOnClickListener {
            itemClickListener?.onItemClick(movie, holder.imageviewMovie.height, holder.itemView.y.toInt())
        }

    }

    fun updateMovieList(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun addItemClickListener(listClickListener: ListClickListener<Movie>) {
        this.itemClickListener = listClickListener
    }

}

class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val imageviewMovie = view.imageViewMovie
    val textViewTitle = view.textViewTitle

}

interface ListClickListener<T> {
    fun onItemClick(data:T, height: Int, y: Int)
}
