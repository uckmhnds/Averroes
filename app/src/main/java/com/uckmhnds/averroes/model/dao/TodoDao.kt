package com.uckmhnds.averroes.model.dao

import androidx.room.*
import com.uckmhnds.averroes.model.entities.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun getTodosByIdAsc(): Flow<List<Todo>>

    @Query("SELECT COUNT(id) FROM todos")
    fun getDataCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("DELETE FROM todos")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(todo: Todo)

}