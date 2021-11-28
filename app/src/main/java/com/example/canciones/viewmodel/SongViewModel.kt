package com.example.canciones.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.canciones.database.Song
import com.example.canciones.database.SongDatabase
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val db: SongDatabase = SongDatabase.getInstance(application)

    val mAllSongs: LiveData<List<Song>> = db.songDao().getAllSongs()

    var displayText: String = ""

    private fun insert(song: Song){
        viewModelScope.launch {
            db.songDao().insert(song)
        }
    }

    private fun deleteAll(){
        viewModelScope.launch {
            db.songDao().deleteALlSongs()
        }
    }

    fun addNewSong(title: String, artist: String, author: String, year: String){
        val newSong = Song(title, artist, author, year.toInt())
        insert(newSong)
    }

    fun deleteAllSongs(){
        deleteAll()
    }


}