package com.uckmhnds.averroes.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uckmhnds.averroes.data.room.entities.Note
import com.uckmhnds.averroes.data.room.dao.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor (private val noteDao: NoteDao) {

    val notes: Flow<List<Note>>             = noteDao.getNotesByIdAsc()
    var size: Flow<Int>                     = noteDao.getDataCount()

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun getAllNotes(): Flow<List<Note>>{
//        return noteDao.getNotesByIdAsc()
//    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun getQueryNotes(query: String): Flow<List<Note>>{
//        return noteDao.getQueryNotes(query)
//    }

    fun getQueryNotes(query: String)    = noteDao.getQueryNotes(query)

    fun getAllNotes()                   = noteDao.getNotesByIdAsc()

    suspend fun insert(note: Note)      = noteDao.insert(note)

    suspend fun deleteAll()             = noteDao.deleteAll()

    suspend fun delete(note: Note)      = noteDao.delete(note)

    suspend fun update(note: Note)      = noteDao.update(note)

    suspend fun getNoteById(id: Int)    = noteDao.getNoteById(id)


    private var _searchResults: MutableLiveData<List<Note>>     = MutableLiveData(mutableListOf())
    var searchResults:LiveData<List<Note>>                      = _searchResults

    @WorkerThread
    fun search(searchText: String){
        val result       = noteDao.search(searchText)
        _searchResults.postValue(result)
    }

}