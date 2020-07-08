package com.howtodoandroid.roomdatabasemigration

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    private val hp = null
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "create table table1 " +
                    "(id integer primary key, name1 text,phone1 text,email1 text)"
        )

        db.execSQL(
            "create table table2 " +
                    "(id integer primary key, name2 text,phone2 text,email2 text)"
        )

        db.execSQL(
            "create table table3 " +
                    "(id integer primary key, name3 text,phone3 text,email3 text)"
        )

        db.execSQL(
            "create table table4 " +
                    "(id integer primary key, name4 text,phone4 text,email4 text)"
        )

        db.execSQL(
            "create table table5 " +
                    "(id integer primary key, name5 text,phone5 text,email5 text)"
        )
    }



    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts")
        onCreate(db)
    }

    fun insertContact(
        name: String?,
        phone: String?,
        email: String?
    ): Boolean {
        val db = this.writableDatabase
        val contentValues1 = ContentValues()
        contentValues1.put("name1", name)
        contentValues1.put("phone1", phone)
        contentValues1.put("email1", email)
        db.insert("table1", null, contentValues1)

        val contentValues2 = ContentValues()
        contentValues2.put("name2", name)
        contentValues2.put("phone2", phone)
        contentValues2.put("email2", email)
        db.insert("table2", null, contentValues2)

        val contentValues3 = ContentValues()
        contentValues3.put("name3", name)
        contentValues3.put("phone3", phone)
        contentValues3.put("email3", email)
        db.insert("table3", null, contentValues3)

        val contentValues4 = ContentValues()
        contentValues4.put("name4", name)
        contentValues4.put("phone4", phone)
        contentValues4.put("email4", email)
        db.insert("table4", null, contentValues4)

        val contentValues5 = ContentValues()
        contentValues5.put("name5", name)
        contentValues5.put("phone5", phone)
        contentValues5.put("email5", email)
        db.insert("table5", null, contentValues5)

        return true
    }

    companion object {
        const val DATABASE_NAME = "MyDBName.db"
        const val CONTACTS_TABLE_NAME = "contacts"
        const val CONTACTS_COLUMN_ID = "id"
        const val CONTACTS_COLUMN_NAME = "name"
        const val CONTACTS_COLUMN_EMAIL = "email"
        const val CONTACTS_COLUMN_STREET = "street"
        const val CONTACTS_COLUMN_CITY = "place"
        const val CONTACTS_COLUMN_PHONE = "phone"
    }
}
