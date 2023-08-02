package com.example.androidtestingsample.di

import com.example.androidtestingsample.data.MainRepository
import com.example.androidtestingsample.data.MainRepositoryImpl
import com.example.androidtestingsample.data.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [ViewModelComponent::class], replaces = [RepositoriesModule::class])
interface TestRepositoriesModule {

    @Binds
    fun mainRepository(mainRepositoryImpl: TestRepository): MainRepository
}