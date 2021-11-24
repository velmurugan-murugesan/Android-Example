package com.example.kotlincoroutines

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlincoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var adapter: DashboardMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DashboardMenuAdapter()
        binding.recyclerview.adapter = adapter

        mainViewModel.movieList.observe(this) {
            adapter.addMovieList(it)
        }

        binding.recyclerview.visibility = View.GONE

        binding.startPrint.setOnClickListener {
            mainViewModel.startPrintWithTimeout()
        }

        binding.stopPrint.setOnClickListener {
            mainViewModel.stopPrint()
        }

        mainViewModel.startPrintWithTimeout()

    }



}