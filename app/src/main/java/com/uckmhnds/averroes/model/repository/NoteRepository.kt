package com.uckmhnds.averroes.model.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.dao.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val notes: Flow<List<Note>>    = noteDao.getNotesByIdAsc()
    var size: Flow<Int>            = noteDao.getDataCount()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        noteDao.deleteAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(note: Note){
        noteDao.update(note)
    }

    private var _searchResults: MutableLiveData<List<Note>>     = MutableLiveData(mutableListOf())
    var searchResults:LiveData<List<Note>>                      = _searchResults

    @WorkerThread
    fun search(searchText: String){
        val result       = noteDao.search(searchText)
        _searchResults.postValue(result)
    }

}