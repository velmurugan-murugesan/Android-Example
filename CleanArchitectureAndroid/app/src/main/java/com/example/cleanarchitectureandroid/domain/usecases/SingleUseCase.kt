package com.example.cleanarchitectureandroid.domain.usecases

import io.reactivex.Single

interface SingleUseCase<T> {

    fun execute() : Single<T>
}