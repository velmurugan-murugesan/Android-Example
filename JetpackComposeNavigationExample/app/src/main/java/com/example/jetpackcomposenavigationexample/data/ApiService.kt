package com.example.jetpackcomposenavigationexample.data

import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllMovies() : List<Movie>

    @GET("moviedetail.json")
    suspend fun getMovieDetails()  : MovieDetails

}