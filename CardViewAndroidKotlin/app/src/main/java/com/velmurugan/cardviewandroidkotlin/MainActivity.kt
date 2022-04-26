package com.velmurugan.cardviewandroidkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var movieList = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieList = ArrayList()
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerViewAdapter = RecyclerViewAdapter(movieList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerViewAdapter!!.setOnItemClickListener(object : ClickListener<Movie> {
            override fun onItemClick(data: Movie) {
                Toast.makeText(this@MainActivity, data.title, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView!!.adapter = recyclerViewAdapter
        prepareMovie()
    }

    private fun prepareMovie() {
        var movie = Movie("Star Wars The Last Jedi", R.drawable.star_war, 8.0F)
        movieList.add(movie)
        movie = Movie("Coco", R.drawable.coco,8.3F)
        movieList.add(movie)
        movie = Movie("Justice League", R.drawable.justice_league,9.0F)
        movieList.add(movie)
        movie = Movie("Thor: Ragnarok", R.drawable.thor_ragnarok, 8.5F)
        movieList.add(movie)
        movie = Movie("Star Wars The Last Jedi", R.drawable.star_war, 8.4F)
        movieList.add(movie)
        movie = Movie("Coco", R.drawable.coco,8.2F)
        movieList.add(movie)
        movie = Movie("Justice League", R.drawable.justice_league,8.4F)
        movieList.add(movie)
        movie = Movie("Thor: Ragnarok", R.drawable.thor_ragnarok, 8.9F)
        movieList.add(movie)
        movie = Movie("Star Wars The Last Jedi", R.drawable.star_war, 8.4F)
        movieList.add(movie)
        movie = Movie("Coco", R.drawable.coco, 9.0F)
        movieList.add(movie)
        movie = Movie("Justice League", R.drawable.justice_league,9.2F)
        movieList.add(movie)
        movie = Movie("Thor: Ragnarok", R.drawable.thor_ragnarok, 9.4F)
        movieList.add(movie)
        recyclerViewAdapter?.notifyDataSetChanged()
    }
}