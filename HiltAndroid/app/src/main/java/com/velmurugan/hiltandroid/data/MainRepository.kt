package com.velmurugan.hiltandroid.data

import com.velmurugan.hiltandroid.Movie
import retrofit2.Response

interface MainRepository {

    fun saveMovie(movieList: List<Movie>)
    suspend fun getAllMovies() : Response<List<Movie>>

    suspend fun getVersion() : Response<Int>


}