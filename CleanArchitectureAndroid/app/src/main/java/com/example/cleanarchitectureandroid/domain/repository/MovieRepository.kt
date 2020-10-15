package com.example.cleanarchitectureandroid.domain.repository

import com.example.cleanarchitectureandroid.data.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getAllMovies() : Single<List<Movie>>
}