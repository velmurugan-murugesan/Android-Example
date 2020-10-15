package com.example.cleanarchitectureandroid.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.MovieDetail
import com.example.cleanarchitectureandroid.domain.usecases.MovieDetailUseCase
import com.example.cleanarchitectureandroid.rx.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailUseCase, private val scheduler: SchedulersProvider) : ViewModel() {

    val movieDetail = MutableLiveData<MovieDetail>()
    private val disposable = CompositeDisposable()
    val errorMessage = MutableLiveData<String>()

    fun fetchAllMovies() {

        disposable.add(movieDetailsUseCase.execute()
            .subscribeOn(scheduler.io())
            .subscribe({
                it?.let {
                    movieDetail.postValue(it)
                }
            },{
                it?.let {
                    errorMessage.postValue(it.message)
                }
            })
        )
    }


    override fun onCleared() {
        disposable.clear()
    }



}