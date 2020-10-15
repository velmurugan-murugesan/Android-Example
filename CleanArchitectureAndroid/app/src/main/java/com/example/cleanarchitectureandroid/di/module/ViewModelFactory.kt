package com.example.cleanarchitectureandroid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
private typealias ViewModelProvidersMap = Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

class MyViewModelFactory @Inject constructor(
    private val creators: ViewModelProvidersMap
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var viewModelProvider = creators[modelClass]

        if (viewModelProvider == null) {
            val entries = creators.entries
            val mapEntry = entries.firstOrNull {
                modelClass.isAssignableFrom(it.key)
            } ?: throw IllegalArgumentException("Unknown model class $modelClass")
            viewModelProvider = mapEntry.value
        }

        try {
            @Suppress("UNCHECKED_CAST")
            return viewModelProvider.get() as T
        } catch (e: Throwable) {
            throw IllegalArgumentException("Couldn't create ViewModel with specified class $modelClass", e)
        }
    }
}