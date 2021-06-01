package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() = retrofitService.getAllMovies()

}