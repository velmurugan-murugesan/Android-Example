package com.example.cleanarchitectureandroid.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.di.module.ViewModelModule
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)


        detailViewModel.movieDetail.observe(this, Observer {

            it?.let {
                textTitle.text = it.title
                textDesc.text = it.desc
                textDate.text = it.date
                Glide.with(this).load(it.image).into(poster)

            }

        })

        detailViewModel.fetchAllMovies()
    }

}