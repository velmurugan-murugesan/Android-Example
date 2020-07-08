package com.howtodoandroid.roomdatabasemigration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {

    }
}
@Database(entities = [Table1::class,Table2::class], version = 2, exportSchema = false)
abstract class Database1 : RoomDatabase() {
    abstract fun table1Dao() : Table1Dao



    companion object {
        private var INSTANCE: Database1? = null

        fun getInstance(context: Context): Database1? {
            if (INSTANCE == null) {
                synchronized(Database1::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        Database1::class.java, DBHelper.DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}