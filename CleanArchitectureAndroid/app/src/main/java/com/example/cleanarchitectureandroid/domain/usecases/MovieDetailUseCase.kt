package com.example.cleanarchitectureandroid.domain.usecases

import com.example.cleanarchitectureandroid.data.MovieDetail
import com.example.cleanarchitectureandroid.data.MovieDetailRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieDetailRepositoryImpl: MovieDetailRepositoryImpl): SingleUseCase<MovieDetail> {
    override fun execute(): Single<MovieDetail> {
        return movieDetailRepositoryImpl.gertMovieDetail()
    }

}