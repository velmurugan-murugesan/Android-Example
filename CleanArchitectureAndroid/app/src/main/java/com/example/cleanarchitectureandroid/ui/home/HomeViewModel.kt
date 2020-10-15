package com.example.cleanarchitectureandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.domain.usecases.GetAllMoviesUseCase
import com.example.cleanarchitectureandroid.rx.SchedulersFacade
import com.example.cleanarchitectureandroid.rx.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase, private val scheduler: SchedulersProvider) : ViewModel() {

    private val disposable = CompositeDisposable()
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchAllMovies() {

        disposable.add(getAllMoviesUseCase.execute()
            .subscribeOn(scheduler.io())
            .subscribe({
                it?.let {
                    movieList.postValue(it)
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