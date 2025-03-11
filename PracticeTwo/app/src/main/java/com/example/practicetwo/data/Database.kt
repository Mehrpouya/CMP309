package com.example.practicetwo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Database configures the Room database with:
 * - an array of all entities (in this case [Note::class])
 * - a version number (start at 1, increment if you do a schema migration)
 * - exportSchema (optional) set to false means you won't generate
 *   a schema file for migrations.
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    /**
     * This is how we connect to the DAO. Room will generate
     * the actual implementation for noteDao() at compile time.
     */
    abstract fun noteDao(): NoteDao

    companion object {
        /**
         * @Volatile means writes to this field are immediately visible
         * to other threads.
         */
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * We use a singleton pattern so we only build
         * the database once per app lifecycle.
         */
        fun getDatabase(context: Context): NoteDatabase {
            // If INSTANCE is not null, return it,
            // otherwise synchronize to create it.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    // You could add .fallbackToDestructiveMigration() here
                    // for testing, if you change schemas.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}