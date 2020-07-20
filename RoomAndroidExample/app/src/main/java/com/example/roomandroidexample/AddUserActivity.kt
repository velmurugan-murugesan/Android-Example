package com.example.roomandroidexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {


    var user: Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        user = intent.getParcelableExtra("user")

        user?.let {
            ed_username.setText(it.userName)
            ed_location.setText(it.location)
            ed_email.setText(it.email)
        } ?: kotlin.run {

        }

        val repo = UserRepository(this)

        button_save_user.setOnClickListener {

            if (ed_username.text.isNotEmpty() && ed_email.text.isNotEmpty() && ed_location.text.isNotEmpty()) {

                user?.let {
                    val user = Users(userId = it.userId,
                        userName = ed_username.text.toString(),
                        location = ed_location.text.toString(),
                        email = ed_email.text.toString()
                    )
                    repo.updateUser(user)
                } ?: kotlin.run {
                    val user = Users(
                        userName = ed_username.text.toString(),
                        location = ed_location.text.toString(),
                        email = ed_email.text.toString()
                    )
                    repo.insertUser(user)
                }

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

}