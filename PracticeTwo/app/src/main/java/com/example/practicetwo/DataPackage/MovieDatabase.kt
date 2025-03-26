package com.example.practicetwo.DataPackage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//Entities is an array. These are the classes that represent the tables in my database. Please create and manage them
//exportSchema is a boolean flag that determines whether Room should export the database schema to a JSON file.
@Database(entities = [Movie::class], version = 5,exportSchema = true)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            //it will be forced to wait until the lock is released.
            return INSTANCE ?: synchronized(this) {//We use synchronised for thread safety
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

