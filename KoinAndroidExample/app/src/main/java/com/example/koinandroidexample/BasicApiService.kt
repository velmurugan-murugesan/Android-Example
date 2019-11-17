package com.example.koinandroidexample


import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.POST

interface BasicApiService {


    @GET("users")
    fun getAllUsers() : Single<List<Users>>
}