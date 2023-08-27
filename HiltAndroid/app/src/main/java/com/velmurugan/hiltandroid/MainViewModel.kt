package com.velmurugan.hiltandroid

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velmurugan.hiltandroid.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

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