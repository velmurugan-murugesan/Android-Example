package com.example.cleanarchitectureandroid.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.di.module.MyViewModelFactory
import com.example.cleanarchitectureandroid.ui.detail.DetailActivity
import com.example.cleanarchitectureandroid.utils.ClickListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        movieAdapter = MovieAdapter()

        recyclerviewMovies.adapter = movieAdapter

        homeViewModel = ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)

        homeViewModel.movieList.observe(this, Observer {
                it?.let {
                    movieAdapter.setMovies(it)
                }
        })

        movieAdapter.setMovieClickListener(object : ClickListener<Movie>{
            override fun onCLick(data: Movie) {
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
            }

        })

        homeViewModel.fetchAllMovies()

    }


}