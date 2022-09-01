package com.uckmhnds.averroes.model.database

import androidx.room.*
import com.uckmhnds.averroes.model.entities.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun getTodosById(): Flow<List<Todo>>

    @Query("DELETE FROM todos")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

}