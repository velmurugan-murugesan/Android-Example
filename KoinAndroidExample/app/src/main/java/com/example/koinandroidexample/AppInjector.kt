package com.example.koinandroidexample

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val networkModule = module {
    single { createBasicAuthService() }
    single { RxSingleSchedulers.DEFAULT }
}

val viewModelModule = module {

    viewModel {
        MainViewModel(get(), get())
    }
}