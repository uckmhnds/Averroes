package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.uckmhnds.averroes.data.room.entities.Todo
import com.uckmhnds.averroes.data.repository.TodoRepository
import com.uckmhnds.averroes.domain.mapper.TodoMapper
import com.uckmhnds.averroes.domain.model.TodoModel
import com.uckmhnds.averroes.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor (private val todoMapper: TodoMapper, private val todoRepository: TodoRepository): BaseViewModel() {

    fun pushTodo(todo: Todo)                = viewModelScope.launch{
        todoRepository.insert(todo)
    }

    fun deleteAll()                         = viewModelScope.launch {
        todoRepository.deleteAll()
    }

    fun delete(todo: Todo)                  = viewModelScope.launch {
        todoRepository.delete(todo)
    }

    val todos: LiveData<List<TodoModel>>    = entityMapper(todoRepository.todos)
    val size: LiveData<Int>                 = todoRepository.size.asLiveData()

    private fun entityMapper(todoEntityFlow: Flow<List<Todo>>): LiveData<List<TodoModel>>{
        return todoMapper.mapTodoEntity(todoEntityFlow)
    }


}