package com.example.swiperefreshlayoutandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter
    private val apiInterface = ApiInterface.create().getMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerAdapter = RecyclerAdapter(this)
        recyclerview.adapter = recyclerAdapter
        swipeRefreshLayout.setOnRefreshListener(refreshListener)
        fetchMovieList()
    }


    private fun fetchMovieList() {
        apiInterface.clone().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.body() != null)
                    recyclerAdapter.setMovieListItems(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val refreshListener = OnRefreshListener {
        swipeRefreshLayout.isRefreshing = true
        recyclerAdapter.clearData()
        //fetchMovieList()
    }

}