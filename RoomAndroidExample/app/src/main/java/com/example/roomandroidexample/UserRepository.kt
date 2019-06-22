package com.example.roomandroidexample

import android.content.Context
import android.os.AsyncTask

class UserRepository(context: Context) {

    var db: UserDao = AppDatabase.getInstance(context)?.userDao()!!

    fun getAllUsers(): List<Users> {
        return db.gelAllUsers()
    }

    fun insertUser(users: Users) {
        insertAsyncTask(db).execute(users)
    }

    private class insertAsyncTask internal constructor(private val usersDao: UserDao) :
        AsyncTask<Users, Void, Void>() {

        override fun doInBackground(vararg params: Users): Void? {
            usersDao.insertUser(params[0])
            return null
        }
    }
}