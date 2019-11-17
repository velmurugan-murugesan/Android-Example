package com.example.koinandroidexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var userAdapter: UserAdapter
    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter()
        recyclerview.adapter = userAdapter

        mainViewModel.userList.observe(this, Observer {

            it?.let {
                userAdapter.setItems(it)
            }

        })

        mainViewModel.fetchUsers()



    }
}
