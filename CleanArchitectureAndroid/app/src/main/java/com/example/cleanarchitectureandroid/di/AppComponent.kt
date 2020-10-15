package com.example.cleanarchitectureandroid.di

import android.app.Application
import com.example.cleanarchitectureandroid.AppApplication
import com.example.cleanarchitectureandroid.di.module.ActivityModule
import com.example.cleanarchitectureandroid.di.module.AppModule
import com.example.cleanarchitectureandroid.di.module.NetworkModule
import com.example.cleanarchitectureandroid.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, ViewModelModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<AppApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

}