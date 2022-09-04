package com.uckmhnds.averroes.model.dao

import androidx.room.*
import com.uckmhnds.averroes.model.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getNotesByIdAsc(): Flow<List<Note>>

    @Query("SELECT COUNT(id) FROM notes")
    fun getDataCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

}