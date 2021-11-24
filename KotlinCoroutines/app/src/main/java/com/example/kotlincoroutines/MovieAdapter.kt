package com.example.kotlincoroutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlincoroutines.databinding.AdapterMovieBinding

class DashboardMenuAdapter : RecyclerView.Adapter<MenuViewHolder>() {
    private var movieList = mutableListOf<Movie>()

    fun addMovieList(menuList: List<Movie>) {
        this.movieList = menuList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val movie = movieList[position]

        holder.adapterMovieBinding.apply {

            Glide.with(image.context).load(movie.imageUrl).into(image)

            textTitle.text = movie.name
            textCategory.text = movie.category
            textDesc.text = movie.desc


        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}

class MenuViewHolder(val adapterMovieBinding: AdapterMovieBinding) :
    RecyclerView.ViewHolder(adapterMovieBinding.root)

