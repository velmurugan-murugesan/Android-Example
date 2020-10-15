package com.example.cleanarchitectureandroid.data

import com.example.cleanarchitectureandroid.domain.repository.MovieDetailRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiClient: ApiClient) : MovieDetailRepository {
    override fun gertMovieDetail(): Single<MovieDetail> {
        return apiClient.getMovieDetail()
    }
}