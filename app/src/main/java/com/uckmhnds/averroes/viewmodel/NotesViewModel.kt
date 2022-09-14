package com.uckmhnds.averroes.viewmodel

import androidx.lifecycle.*
import com.uckmhnds.averroes.data.preferences.AppPreferences
import com.uckmhnds.averroes.data.room.entities.Note
import com.uckmhnds.averroes.data.repository.NoteRepository
import com.uckmhnds.averroes.domain.mapper.NoteMapper
import com.uckmhnds.averroes.domain.model.NoteModel
import com.uckmhnds.averroes.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor (private val noteMapper: NoteMapper, private val noteRepository: NoteRepository):BaseViewModel()
{


    val notes:LiveData<List<NoteModel>>                 = entityMapper(noteRepository.notes)
    val size: LiveData<Int>                             = noteRepository.size.asLiveData()

    private fun entityMapper(noteEntityFlow: Flow<List<Note>>): LiveData<List<NoteModel>>{
        return noteMapper.mapNoteEntity(noteEntityFlow)
    }

    private fun modelMapper(noteModel: NoteModel): Note{
        return noteMapper.mapNoteModel(noteModel)
    }

    fun pushNote(noteModel: NoteModel){
        viewModelScope.launch{

            noteRepository.insert(modelMapper(noteModel))

        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            noteRepository.deleteAll()
        }
    }

//    private val _notes                                  = noteUseCase.notes
//    val notes                = noteUseCase.notes



//    init {
//        getAllNotes()
//
//    }
//
//    private fun getAllNotes(){
//        viewModelScope.launch {
//
//            _notes.postValue(noteUseCase.getAllNotes().value)
//
//        }
//    }

//    suspend fun getAllNotes()   = noteUseCase.getAllNotes()
//    suspend fun getAllNotes()   = noteRepository.notes

//    fun get(){
//        viewModelScope.launch {
//            _notes.postValue(getAllNotes().value)
//        }
//    }

//    val notes   = noteRepository.notes.asLiveData()



//    suspend fun getAllNotes(): LiveData<List<NoteModel>>                               = noteUseCase.getAllNotes()

//    private fun getAllNotes()
//    {
//
//        viewModelScope.launch {
//            _notes.postValue(noteUseCase.getAllNotes().value)
//            Log.i("getAllNotes", noteUseCase.getAllNotes().value.toString())
//        }
//
//    }

//    fun getSearchedNotes(query: String){
//
//        viewModelScope.launch {
//            _notes.postValue(noteUseCase.getQueryNotes(query))
//        }
//
//    }



}

