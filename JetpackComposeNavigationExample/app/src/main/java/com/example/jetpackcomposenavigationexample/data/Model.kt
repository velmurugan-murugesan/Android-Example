package com.example.jetpackcomposenavigationexample.data


data class Movie(val name: String, val desc: String, val category: String, val imageUrl: String)

data class MovieDetails(val name: String, val desc: String, val category: String, val images: List<String>)