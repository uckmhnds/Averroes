package com.uckmhnds.averroes.model.repository

import androidx.annotation.WorkerThread
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.database.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

     val notes: Flow<List<Note>>    = noteDao.getNotesByIdAsc()

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

}