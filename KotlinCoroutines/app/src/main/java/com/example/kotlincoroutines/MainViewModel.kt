package com.example.kotlincoroutines

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.Exception

//1 - using try catch
// 1.1 - returning default value on exception
//2 - using coroutine exception handler
//3 - Cancellation
// 3.1 - finally
//4 - Timeout


class MainViewModel() : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()


    var printingJob: Job? = null


    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("Exception", "${exception.message.toString()} handled !")
    }

    fun handleExceptionWithCoroutineExceptionHandler() {
        viewModelScope.launch(handler) {
            val moviesList = ApiService.getInstance().getAllMovies401()
            movieList.postValue(moviesList)
        }
    }

    fun handleExceptionWithTryCatch() {
        viewModelScope.launch() {
            try {
                val movies = ApiService.getInstance().getAllMovies401()
                movieList.postValue(movies)
            } catch (e: Exception) {
                Log.d("Exception", "${e.message.toString()} handled !")
            }
        }
    }

    //First API success and second API call fails
    fun handleExceptionOnMultipleApiCall() {

        viewModelScope.launch {
            try {
                val movies1 = ApiService.getInstance().getAllMovies()
                val movies2 = ApiService.getInstance().getAllMovies401()
            } catch (e: Exception) {
                Log.d("Exception", "${e.message.toString()} handled !")
            }
        }
    }

    fun returnDefaultResultOnException() {
        viewModelScope.launch {
            try {
                val movie1 = try {
                    ApiService.getInstance().getAllMovies()
                } catch (e: Exception) {
                    listOf()
                }

                val movie2 = try {
                    ApiService.getInstance().getAllMovies401()
                } catch (e: Exception) {
                    listOf()
                }

                Log.d("Movie 1", movie1.toString())
                Log.d("Movie 2", movie2.toString())

            } catch (e: Exception) {
                Log.d("Exception", "${e.message.toString()} handled !")
            }

        }

    }


    fun startPrint() {

        printingJob = viewModelScope.launch {

            try {
                for (i in 1..1000) {
                    delay(20)
                    println(i)
                }
            } catch (e: Exception) {
                Log.d("Exception", "${e.message.toString()} handled !")
            } finally {
                Log.d("finally", "clear all resources")
            }
        }
    }

    fun stopPrint() {
        printingJob?.cancel()
    }

    fun startPrintWithTimeout() {

        viewModelScope.launch {
            try {
                withTimeout(2000) {
                    for (i in 1..1000) {
                        delay(20)
                        println(i)
                    }
                }
            } catch (e: Exception) {
                Log.d("Exception", "${e.message.toString()} handled !")
            }
            finally {
                Log.d("finally", "clear all resources")
            }
        }
    }
}