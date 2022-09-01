package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.*
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.repository.NoteRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NotesViewModel(private val noteRepo: NoteRepository) : ViewModel() {

//    fun insert(note: Note)      = viewModelScope.launch{
//        noteRepo.insert(note)
//    }

    fun deleteAll()             = viewModelScope.launch {
        noteRepo.deleteAll()
    }

    fun delete(note: Note)      = viewModelScope.launch {
        noteRepo.delete(note)
    }

    val notes: LiveData<List<Note>>     = noteRepo.notes.asLiveData()

}