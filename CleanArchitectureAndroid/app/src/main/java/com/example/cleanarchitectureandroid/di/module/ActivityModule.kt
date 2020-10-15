package com.example.cleanarchitectureandroid.di.module

import com.example.cleanarchitectureandroid.ui.detail.DetailActivity
import com.example.cleanarchitectureandroid.ui.home.HomeActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideHomeActivity() : HomeActivity

    @ContributesAndroidInjector
    abstract fun provideDetailActivity() : DetailActivity


}