package com.howtodoandroid.roomdatabasemigration

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Table1Dao {

    @Insert
    fun insertUser(table1: Table1)


    @Query("Select * from table1")
    fun fetchAll() : List<Table1>

    @Query("Select * from table2")
    fun fetchAll2() : List<Table2>


}