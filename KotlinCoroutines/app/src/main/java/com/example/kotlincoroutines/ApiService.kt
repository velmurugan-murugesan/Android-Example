package com.example.kotlincoroutines

import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movielist_200.json")
    suspend fun getAllMovies(): List<Movie>

    @GET("movielist_401.json")
    suspend fun getAllMovies401(): List<Movie>
    companion object {
        var apiService: ApiService? = null;

        fun getInstance(): ApiService {

            if (apiService == null) {
                apiService = retrofit2.Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
                    .build().create(ApiService::class.java)
            }

            return apiService!!

        }


    }
}