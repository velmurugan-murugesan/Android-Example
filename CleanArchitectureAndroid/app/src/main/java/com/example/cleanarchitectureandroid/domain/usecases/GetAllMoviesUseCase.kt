package com.example.cleanarchitectureandroid.domain.usecases

import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.MovieRepositoryImpl
import io.reactivex.Single
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val movieRepositoryImpl: MovieRepositoryImpl) : SingleUseCase<List<Movie>> {

    override fun execute(): Single<List<Movie>> {
        return movieRepositoryImpl.getAllMovies()
    }

}