package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uckmhnds.averroes.model.repository.NoteRepository
import com.uckmhnds.averroes.model.repository.TodoRepository
import java.lang.IllegalArgumentException

class SharedTodoViewModelFactory(private val todoRepo: TodoRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedTodoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SharedTodoViewModel(todoRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}