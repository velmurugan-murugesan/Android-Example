package com.example.cleanarchitectureandroid.ui.home

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.utils.ClickListener
import kotlinx.android.synthetic.main.adapter_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()

    var clickListener: ClickListener<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.title
        Glide.with(holder.itemView.context)
            .load(movie.image)
            .into(holder.posterImager)

        holder.posterImager.setOnClickListener {
            clickListener?.onCLick(movie)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun setMovieClickListener(clickListener: ClickListener<Movie>) {
        this.clickListener = clickListener
    }

}

class MovieViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val posterImager = view.imageViewPoster
    val movieTitle = view.textViewTitle
}
