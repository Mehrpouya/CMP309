package com.example.practicetwo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //This class will have a mapping SQLite table in the database.
data class MoviesDatabase(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "movie_title") val title: String,
    @ColumnInfo(name = "director", index = true) val director: String,
    @ColumnInfo(name = "move_poster", typeAffinity = ColumnInfo.BLOB) val moviePoster: ByteArray // Store as BLOB

    val year: Int
)