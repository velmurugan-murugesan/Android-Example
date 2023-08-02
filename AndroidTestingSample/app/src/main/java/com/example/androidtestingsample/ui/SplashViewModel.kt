package com.example.androidtestingsample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestingsample.data.MainRepository
import com.example.androidtestingsample.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {


        val movieList = MutableLiveData<List<Movie>>()

        fun fetchAllMovies() {
            viewModelScope.launch {
                kotlin.runCatching {
                    mainRepository.getAllMovies()
                }.onSuccess {
                    movieList.postValue(it)
                }.onFailure {

                }
            }
        }
}