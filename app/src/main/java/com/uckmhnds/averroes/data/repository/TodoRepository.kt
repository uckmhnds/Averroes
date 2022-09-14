package com.uckmhnds.averroes.data.repository

import androidx.annotation.WorkerThread
import com.uckmhnds.averroes.data.room.dao.TodoDao
import com.uckmhnds.averroes.data.room.entities.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor (private val todoDao: TodoDao) {

    val todos: Flow<List<Todo>>     = todoDao.getTodosByIdAsc()
    var size: Flow<Int>             = todoDao.getDataCount()

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