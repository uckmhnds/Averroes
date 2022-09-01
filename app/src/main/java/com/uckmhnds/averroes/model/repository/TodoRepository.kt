package com.uckmhnds.averroes.model.repository

import androidx.annotation.WorkerThread
import com.uckmhnds.averroes.model.database.TodoDao
import com.uckmhnds.averroes.model.entities.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val todos: Flow<List<Todo>>     = todoDao.getTodosById()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        todoDao.deleteAll()
    }

}