package com.example.canciones.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(
    @PrimaryKey
    val title: String,
    var author: String,
    var album: String,
    var year: Int

)