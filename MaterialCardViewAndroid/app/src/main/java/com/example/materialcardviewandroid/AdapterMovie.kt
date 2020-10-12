package com.example.materialcardviewandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_movie.view.*

class AdapterMovie : RecyclerView.Adapter<ViewHolderMovie>() {

    var movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        return ViewHolderMovie(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        val movie = movieList[position]
        holder.title.text = movie.title
        Glide.with(holder.itemView.context).load(movie.image).override(600,300).into(holder.image)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieListItems(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

}

class ViewHolderMovie(view: View) : RecyclerView.ViewHolder(view) {

    val image = view.image
    val title = view.title

}
