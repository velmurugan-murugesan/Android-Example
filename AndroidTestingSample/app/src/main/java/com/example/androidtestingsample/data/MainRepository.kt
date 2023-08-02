package com.example.androidtestingsample.data

import retrofit2.Response

interface MainRepository {
    suspend fun getAllMovies() : List<Movie>
}