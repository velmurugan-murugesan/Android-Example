package com.example.datastorejetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.CorruptionException
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.migrations.SharedPreferencesView
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import kotlinx.android.synthetic.main.activity_datastore.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream

class ProtoDataStoreActivity : AppCompatActivity() {

    val serializer = object : Serializer<Profile> {
        override fun readFrom(input: InputStream): Profile {
            try {
                return Profile.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(
            t: Profile,
            output: OutputStream
        ) = t.writeTo(output)
    }

    val protoDataStore: DataStore<Profile> = createDataStore(
        fileName = "profile.pb",
        serializer = serializer
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datastore)

        val sharedPrefsMigration = SharedPreferencesMigration(
            this@ProtoDataStoreActivity,
            "profile"
        ) { sharedPrefs: SharedPreferencesView, currentData: Profile ->
            // Define the mapping from SharedPreferences to UserPreferences
            currentData
        }

         val dataStore: DataStore<Profile> = createDataStore(
            fileName = "user_prefs.pb",
            serializer = serializer,
            migrations = listOf(sharedPrefsMigration)
        )

        val username: Flow<String> = protoDataStore.data
            .map { profile ->
                // The exampleCounter property is generated from the proto schema.
                profile.username
            }

        buttonSubmit.setOnClickListener {
            val username = editTextusername.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                updateProfile(username)
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

        protoDataStore.updateData { profile ->
            profile.toBuilder()
                .setUsername(username)
                .build()
        }
    }


}