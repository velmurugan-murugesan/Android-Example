package com.example.cleanarchitectureandroid.domain.repository

import com.example.cleanarchitectureandroid.data.MovieDetail
import io.reactivex.Single

interface MovieDetailRepository {

    fun gertMovieDetail() : Single<MovieDetail>
}