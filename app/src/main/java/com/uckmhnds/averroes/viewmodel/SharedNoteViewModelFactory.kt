package com.uckmhnds.averroes.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uckmhnds.averroes.model.repository.NoteRepository
import java.lang.IllegalArgumentException

class SharedNoteViewModelFactory(private val noteRepo: NoteRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedNoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SharedNoteViewModel(noteRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}