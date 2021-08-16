package com.example.recyclerviewitemanimationandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var itemsList = mutableListOf<Movie>();

    fun setMovies(movieList: List<Movie>){
        this.itemsList = movieList.toMutableList()
    }

    class MyViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val container = itemView.findViewById<MaterialCardView>(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie,parent,false);
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position];
        holder.container.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
    }

    override fun getItemCount(): Int {
       return itemsList.size
    }
}