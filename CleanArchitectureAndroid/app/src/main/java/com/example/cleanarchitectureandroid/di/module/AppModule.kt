package com.example.cleanarchitectureandroid.di.module

import com.example.cleanarchitectureandroid.rx.SchedulersFacade
import com.example.cleanarchitectureandroid.rx.SchedulersProvider
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulersProvider

}