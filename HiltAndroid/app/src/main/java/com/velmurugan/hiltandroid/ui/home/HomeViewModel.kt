package com.velmurugan.hiltandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velmurugan.hiltandroid.Movie
import com.velmurugan.hiltandroid.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun fetchAllMovies() {
        progressBarStatus.value = true
        viewModelScope.launch {
            kotlin.runCatching {
                mainRepository.getAllMovies()
            }.onSuccess {
                movieList.postValue(it.body())
            }.onFailure {

            }

        }
        progressBarStatus.value = false
    }


}