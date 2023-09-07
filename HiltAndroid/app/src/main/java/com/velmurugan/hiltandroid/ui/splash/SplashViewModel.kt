package com.velmurugan.hiltandroid.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velmurugan.hiltandroid.Movie
import com.velmurugan.hiltandroid.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val version = MutableLiveData<Int>()
    val progressBarStatus = MutableLiveData<Boolean>()
    fun checkVersion() {
        viewModelScope.launch {
            kotlin.runCatching {
               val response = mainRepository.getVersion()
                response.body()
            }.onSuccess {
                version.value = it
            }.onFailure {

            }
        }
    }

}