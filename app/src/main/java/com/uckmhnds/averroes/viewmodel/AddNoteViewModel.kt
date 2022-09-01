package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.repository.NoteRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository): ViewModel() {

    fun insert(note: Note)          = viewModelScope.launch {
        repository.insert(note)
    }

}