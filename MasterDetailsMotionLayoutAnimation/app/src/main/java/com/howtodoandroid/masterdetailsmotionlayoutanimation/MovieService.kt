package com.howtodoandroid.masterdetailsmotionlayoutanimation

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MovieService {
    @get:GET("volley_array.json")
    val movies: Call<List<Movie>>
}

object ClientApi {
    private var movieService: MovieService? = null
    val client: MovieService?
        get() {
            if (movieService == null) {
                movieService = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://howtodoandroid.com/")
                    .build()
                    .create(MovieService::class.java)
            }
            return movieService
        }
}