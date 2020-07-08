package com.howtodoandroid.roomdatabasemigration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table1")
data class Table1(@PrimaryKey(autoGenerate = true)val id: Int? = 0,
                  val name1: String?, val phone1: String?, val email1: String?)

@Entity(tableName = "table2")
data class Table2(@PrimaryKey(autoGenerate = true)val id: Int? = 0,
                  val name2: String?, val phone2: String?, val email2: String?)


@Entity(tableName = "table3")
data class Table3(@PrimaryKey(autoGenerate = true)val id: Int? = 0,
                  val name3: String?, val phone3: String?, val email3: String?)