package com.velmurugan.hiltandroid.data

import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService): MainRepository {
    override suspend fun getAllMovies() = apiService.getAllMovies()
}