package com.velmurugan.recyclerviewdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val users = listOf<Users>(
        Users(1, "User1", "location1", "image"),
        Users(2, "User2", "location2", "image"),
        Users(3,"User3","location3","image"),
        Users(4,"User4","location4","image"),
        Users(5,"User5","location5","image")
    )

    val userAdapter = UserAdapter(users.toMutableList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter = userAdapter



        val btn = findViewById<Button>(R.id.btnSortDescending)
        btn.setOnClickListener {
            val sortedList  = users.sortedByDescending { it.id }
            userAdapter.setUserList(sortedList)
        }
        val btnAscending = findViewById<Button>(R.id.btnAscending)
        btnAscending.setOnClickListener {
            val sortedList  = users.sortedBy { it.id }
            userAdapter.setUserList(sortedList)
        }

    }
}