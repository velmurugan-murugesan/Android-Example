package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import retrofit2.Response

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error<T>(val response: Response<T>): NetworkState<T>()
}

fun <T> Response<T>.parseResponse(): NetworkState<T> {
    return if (this.isSuccessful && this.body() != null) {
        val responseBody = this.body()
        NetworkState.Success(responseBody!!)
    } else {
        NetworkState.Error(this)
    }
}