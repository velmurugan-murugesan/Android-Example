package com.velmurugan.sqlitetoroom

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MainActivity : AppCompatActivity() {

    lateinit var databaseHelper: DatabaseHelper

    lateinit var usersDatabase: UsersDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)
        usersDatabase = UsersDatabase.createDatabase(this)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val  buttonShow = findViewById<Button>(R.id.buttonShow)

        buttonAdd.setOnClickListener {
            //val id: Long = databaseHelper.insertNote("Hello World")
            val notes = usersDatabase.noteDao().insertAll(Note(null,"dfdfd"))

        }

        buttonShow.setOnClickListener {
            //val list = databaseHelper.allNotes
            //Log.d("TAG", "onCreate:${list.toString()}")
            val notes = usersDatabase.noteDao().getAll()
            Log.d("TAG", "notes:${notes.toString()}")

        }
    }
}