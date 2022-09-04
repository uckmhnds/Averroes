package com.uckmhnds.averroes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.repository.NoteRepository
import kotlinx.coroutines.launch

class SharedNoteViewModel(private val noteRepo: NoteRepository): ViewModel() {

    fun insert(note: Note)              = viewModelScope.launch{
        noteRepo.insert(note)
    }

    fun deleteAll()                     = viewModelScope.launch {
        noteRepo.deleteAll()
    }

    fun delete(note: Note)              = viewModelScope.launch {
        noteRepo.delete(note)
    }

    fun update(note: Note)      = viewModelScope.launch {
        noteRepo.update(note)
    }

    val notes: LiveData<List<Note>>     = noteRepo.notes.asLiveData()

    var size: LiveData<Int>             = noteRepo.size.asLiveData()


    ///////////////////////////////////////////
    private var specificNote: Note?  = null

    fun setSpecificNote(note: Note){
        specificNote                = note
        Log.i(note.text, "set")
    }

    fun getSpecificNote(): Note{
        return specificNote!!
    }

}