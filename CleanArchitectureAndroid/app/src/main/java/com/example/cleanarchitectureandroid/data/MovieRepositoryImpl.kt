package com.example.cleanarchitectureandroid.data

import com.example.cleanarchitectureandroid.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiClient: ApiClient) : MovieRepository {
    override fun getAllMovies(): Single<List<Movie>> {
        return apiClient.getAllMovies()
    }


}