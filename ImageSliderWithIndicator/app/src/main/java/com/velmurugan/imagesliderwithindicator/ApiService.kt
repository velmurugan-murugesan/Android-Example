package com.velmurugan.imagesliderwithindicator

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    suspend fun getAllMovies(): List<Movies>


    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                apiService = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().addInterceptor(logging).build())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}