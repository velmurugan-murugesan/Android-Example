package com.velmurugan.imagesliderwithindicator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    var movieListResponse: List<Movies> by mutableStateOf(listOf())

    fun getAllMovies() {
        viewModelScope.launch {

            try {
                movieListResponse = ApiService.getInstance().getAllMovies()

            } catch (e: Exception) {
                Log.e("Error",e.toString())
            }
        }

    }

}