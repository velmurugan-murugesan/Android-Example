package com.example.roomandroidexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)


        val repo = UserRepository(this)

        button_save_user.setOnClickListener {
            if (ed_username.text.isNotEmpty() && ed_email.text.isNotEmpty() && ed_location.text.isNotEmpty()) {
                val user = Users(
                    userName = ed_username.text.toString(),
                    location = ed_location.text.toString(),
                    email = ed_email.text.toString()
                )
                repo.insertUser(user)
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

}