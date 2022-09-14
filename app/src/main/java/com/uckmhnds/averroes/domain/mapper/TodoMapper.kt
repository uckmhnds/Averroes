package com.uckmhnds.averroes.domain.mapper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.uckmhnds.averroes.data.room.entities.Todo
import com.uckmhnds.averroes.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoMapper @Inject constructor() {

    fun mapTodoEntity(todoFlow: Flow<List<Todo>>): LiveData<List<TodoModel>> {

        return todoFlow.asLiveData().map { todoList ->  todoList.map {todoEntity ->

            TodoModel(
                id          = todoEntity.id,
                text        = todoEntity.text,
                date        = todoEntity.date,
                done        = todoEntity.done

            )
        }}
    }

    fun mapTodoModel(todoModel: TodoModel): Todo {

        Log.i("mapNoteModelByValue", todoModel.id.toString())

        return Todo(
            id          = todoModel.id,
            text        = todoModel.text!!,
            date        = todoModel.date!!,
            done        = todoModel.done!!
        )
    }

}