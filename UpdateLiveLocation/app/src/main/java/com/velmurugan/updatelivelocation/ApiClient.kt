package com.velmurugan.updatelivelocation

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiClient {

    @GET("updateLocation.json")
    fun updateLocation() : Call<LocationResponse>

    companion object {

        var retrofit: Retrofit? = null
        val logging = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        fun getInstance(context: Context) : Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/apis/")
                    .client(OkHttpClient.Builder().addInterceptor(logging).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}