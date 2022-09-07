package com.example.jetpackcomponentspractice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StudentModel::class] , version = 2)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun studentDao() : StudentDAO

    companion object{

        val migration1_2  = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE students ADD COLUMN hobby TEXT NOT NULL")
            }
        }

        @Volatile
        var INSTANCE : StudentDatabase? = null

        fun getDatabaseInstance(context: Context) : StudentDatabase{

            if(INSTANCE == null) {

                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "studentDB"
                    ).addMigrations(migration1_2).build()

                }

            }

            return INSTANCE!!
        }

    }
}