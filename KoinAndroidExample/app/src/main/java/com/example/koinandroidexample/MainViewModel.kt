package com.example.koinandroidexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers

class MainViewModel(val apiService: BasicApiService, val schedulers: RxSingleSchedulers) : ViewModel() {

    val userList = MutableLiveData<List<Users>>()
    fun fetchUsers() {
        apiService.getAllUsers().subscribeOn(Schedulers.io())
            .compose(schedulers.applySchedulers())
            .subscribe({ result -> userList.postValue(result) },
                { throwable ->

                })

    }

}