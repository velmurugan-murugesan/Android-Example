package com.example.ktorandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ktor.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

   lateinit var adapter: RecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecyclerviewAdapter()
        recyclerview.adapter = adapter
        fetchAllMovies()
    }

    @KtorExperimentalAPI
    private fun fetchAllMovies()  {

        CoroutineScope(Dispatchers.IO).launch {
            val movieApi = MovieApi()
            val response = movieApi.fetchAllMovies()
            withContext(Dispatchers.Main) {
                adapter.setMovieItems(response)
            }
        }
    }
}