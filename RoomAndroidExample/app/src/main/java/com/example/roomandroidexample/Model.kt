package com.example.roomandroidexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Users(@PrimaryKey(autoGenerate = true)var userId: Int? = null,
                 val userName: String, var location: String, val email: String)


