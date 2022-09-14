package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.viewModelScope
import com.uckmhnds.averroes.data.room.entities.Note
import com.uckmhnds.averroes.data.repository.NoteRepository
import com.uckmhnds.averroes.domain.mapper.NoteMapper
import com.uckmhnds.averroes.domain.model.NoteModel
import com.uckmhnds.averroes.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel@Inject constructor (private val noteMapper: NoteMapper, private val noteRepository: NoteRepository): BaseViewModel() {

    private fun modelMapper(noteModel: NoteModel): Note {
        return noteMapper.mapNoteModel(noteModel)
    }

//    fun pushNote(noteModel: NoteModel){
//        viewModelScope.launch {
//            noteRepository.insert(modelMapper(noteModel))
//        }
//    }

    fun updateNote(noteModel: NoteModel){
        viewModelScope.launch {

            noteRepository.update(modelMapper(noteModel))

        }
    }

    private lateinit var noteToBeUpdated: Note

    fun getNote(id:Int){
        viewModelScope.launch {
            noteToBeUpdated     = noteRepository.getNoteById(id)
        }
    }





}