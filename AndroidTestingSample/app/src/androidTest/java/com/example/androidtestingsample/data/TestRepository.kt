package com.example.androidtestingsample.data

import com.example.androidtestingsample.data.MainRepository
import com.example.androidtestingsample.data.Movie
import javax.inject.Inject

class TestRepository @Inject constructor(): MainRepository {
    override suspend fun getAllMovies(): List<Movie> {
        return MOVIE_LIST
    }

}