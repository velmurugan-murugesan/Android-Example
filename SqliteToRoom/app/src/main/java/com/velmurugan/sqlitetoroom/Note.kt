package com.velmurugan.sqlitetoroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
    var note: String? = null

    constructor() {}
    constructor( note: String?) {
        this.note = note
    }
    constructor(id: Int?, note: String?) {
        this.id = id
        this.note = note
    }

    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
        const val COLUMN_NOTE = "note"
        // Create table SQL query
        const val CREATE_TABLE =
            ("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE + " TEXT " + ")")
    }
}