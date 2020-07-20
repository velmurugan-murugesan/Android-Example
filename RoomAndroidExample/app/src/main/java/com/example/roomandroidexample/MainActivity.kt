package com.example.roomandroidexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    val repo:UserRepository by lazy {
        UserRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = UserListAdapter()
        recyclerview_users.layoutManager = LinearLayoutManager(this)
        recyclerview_users.adapter = adapter

        adapter.setOnItemClick(object : ListClickListener<Users>{
            override fun onClick(data: Users, position: Int) {
                val intent = Intent(this@MainActivity,AddUserActivity::class.java)
                intent.putExtra("user", data)
                startActivity(intent)
            }

            override fun onDelete(user: Users) {
                repo.deleteUser(user)
                fetchUsers()
            }
        })


        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllUsers()
        adapter.setUsers(allUsers)
    }
}
