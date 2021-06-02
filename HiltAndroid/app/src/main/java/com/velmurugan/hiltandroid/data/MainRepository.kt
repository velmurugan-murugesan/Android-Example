package com.velmurugan.hiltandroid.data

import com.velmurugan.hiltandroid.Movie
import retrofit2.Response

interface MainRepository {
    suspend fun getAllMovies() : Response<List<Movie>>
}