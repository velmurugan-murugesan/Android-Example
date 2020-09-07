package com.example.datastorejetpack

import androidx.datastore.preferences.preferencesKey

object PreferencesKeys {
    const val PREFERENCE_NAME = "profile"
    val USERNAME = preferencesKey<String>("username")
    val LOCATION = preferencesKey<String>("location")
    val AGE = preferencesKey<Int>("age")
    val IS_ACCOUNT_ACTIVE = preferencesKey<Boolean>("status")
}