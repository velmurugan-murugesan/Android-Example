package com.example.datastorejetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import kotlinx.android.synthetic.main.activity_datastore.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferencesDataStoreActivity : AppCompatActivity() {

    val preferenceDataStore: DataStore<Preferences> by lazy {
        createDataStore(name = "profile")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datastore)

        buttonSubmit.setOnClickListener {
            val username = editTextusername.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                updateProfile(username)
            }
        }

        val username = preferenceDataStore.data.map { profile ->
            profile[PreferencesKeys.USERNAME] ?: ""
        }.catch {exception ->
            if(exception is IOException) {
                //handle exception
            } else {
                throw exception
            }
        }

        buttonRead.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                username.collect {
                    Log.d("username", it)
                }
            }
        }

    }

    private suspend fun updateProfile(username: String) {

        preferenceDataStore.edit { profile ->
            profile[PreferencesKeys.USERNAME] = username
        }
    }



}