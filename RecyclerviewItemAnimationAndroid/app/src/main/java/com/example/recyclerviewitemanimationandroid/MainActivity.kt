package com.example.recyclerviewitemanimationandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview: RecyclerView
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter()
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.adapter = movieAdapter
        movieAdapter.setMovies(getMovieList())
    }


    private fun getMovieList(): MutableList<Movie> {
        val movieList = mutableListOf<Movie>()
        movieList.add(Movie("Movie 1"))
        movieList.add(Movie("Movie 2"))
        movieList.add(Movie("Movie 3"))
        movieList.add(Movie("Movie 4"))
        movieList.add(Movie("Movie 5"))
        movieList.add(Movie("Movie 6"))
        movieList.add(Movie("Movie 7"))
        movieList.add(Movie("Movie 8"))
        movieList.add(Movie("Movie 9"))
        movieList.add(Movie("Movie 10"))
        movieList.add(Movie("Movie 11"))
        movieList.add(Movie("Movie 12"))
        movieList.add(Movie("Movie 1"))
        movieList.add(Movie("Movie 2"))
        movieList.add(Movie("Movie 3"))
        movieList.add(Movie("Movie 4"))
        movieList.add(Movie("Movie 5"))
        movieList.add(Movie("Movie 6"))
        movieList.add(Movie("Movie 7"))
        movieList.add(Movie("Movie 8"))
        movieList.add(Movie("Movie 9"))
        movieList.add(Movie("Movie 10"))
        movieList.add(Movie("Movie 11"))
        movieList.add(Movie("Movie 12"))
        return movieList
    }
}