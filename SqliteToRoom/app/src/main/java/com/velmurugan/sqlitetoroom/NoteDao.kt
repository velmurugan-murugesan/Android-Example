package com.velmurugan.sqlitetoroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg note: Note)
}