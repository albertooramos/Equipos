package com.example.canciones.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(song: Song)

    @Query("SELECT * FROM song_table ORDER BY year ASC")
    fun getAllSongs(): LiveData<List<Song>>

    @Delete
    suspend fun delete(song: Song)

    @Query("DELETE FROM song_table")
    suspend fun deleteALlSongs()
}