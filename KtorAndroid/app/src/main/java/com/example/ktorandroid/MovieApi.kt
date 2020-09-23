package com.example.ktorandroid

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.util.*

class MovieApi {

    //Request
    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }



    @KtorExperimentalAPI
    suspend fun fetchAllMovies(): List<Movie> {
        try {
            return client.get {
                url("https://howtodoandroid.com/movielist.json")
            }
        } catch (e : java.lang.Exception) {
            return emptyList()
        }

    }

}