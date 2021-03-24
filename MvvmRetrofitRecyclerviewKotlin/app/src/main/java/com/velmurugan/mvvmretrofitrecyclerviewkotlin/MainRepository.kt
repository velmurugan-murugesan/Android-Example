package com.velmurugan.mvvmretrofitrecyclerviewkotlin

import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}