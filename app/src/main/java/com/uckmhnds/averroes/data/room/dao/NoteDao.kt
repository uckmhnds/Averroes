package com.uckmhnds.averroes.data.room.dao

import androidx.room.*
import com.uckmhnds.averroes.data.room.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getNotesByIdAsc(): Flow<List<Note>>

    @Query("SELECT COUNT(id) FROM notes")
    fun getDataCount(): Flow<Int>

    @Query("SELECT * FROM notes WHERE title LIKE :searchText OR text LIKE :searchText")
    fun getQueryNotes(searchText: String): List<Note>

    @Query("SELECT * FROM notes WHERE title LIKE :searchText OR text LIKE :searchText")
    fun search(searchText: String): List<Note>

    @Query("SELECT * FROM notes WHERE id LIKE :id")
    fun getNoteById(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

}