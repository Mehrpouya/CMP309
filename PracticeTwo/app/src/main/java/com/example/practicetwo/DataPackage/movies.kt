package com.example.practicetwo.DataPackage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_database")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_title") val title: String,
    @ColumnInfo(name = "director", index = true) val director: String,
    val year: Int,
    @ColumnInfo(name = "movie_poster", typeAffinity = ColumnInfo.BLOB)
    val moviePoster: ByteArray? = null
)

