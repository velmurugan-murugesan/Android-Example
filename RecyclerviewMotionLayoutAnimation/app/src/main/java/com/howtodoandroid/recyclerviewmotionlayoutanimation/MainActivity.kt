package com.howtodoandroid.recyclerviewmotionlayoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MovieAdapter()
        recyclerviewMovies.adapter = adapter
        ClientApi.client?.movies?.enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("Error", t.message)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.isSuccessful) {

                    val movieList = response.body()
                    movieList?.let {
                        adapter?.updateMovieList(it)
                    }

                }
            }
        })
    }
}