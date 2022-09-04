package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.entities.Todo
import com.uckmhnds.averroes.model.repository.TodoRepository
import kotlinx.coroutines.launch

class SharedTodoViewModel(private val todoRepo: TodoRepository): ViewModel() {

    fun insert(todo: Todo)              = viewModelScope.launch{
        todoRepo.insert(todo)
    }

    fun deleteAll()                     = viewModelScope.launch {
        todoRepo.deleteAll()
    }

    fun delete(todo: Todo)              = viewModelScope.launch {
        todoRepo.delete(todo)
    }

    val notes: LiveData<List<Todo>>     = todoRepo.todos.asLiveData()

    var size: LiveData<Int>             = todoRepo.size.asLiveData()

}