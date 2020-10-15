package com.example.cleanarchitectureandroid.data

import com.google.gson.annotations.SerializedName
import javax.inject.Named

data class Movie(val title: String, val desc: String, val image: String)

data class MovieDetail(val title: String, val image: String, val desc: String, val date: String, @SerializedName("Country")val country: String)