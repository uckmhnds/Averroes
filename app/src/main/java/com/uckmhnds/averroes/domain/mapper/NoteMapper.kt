package com.uckmhnds.averroes.domain.mapper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.uckmhnds.averroes.data.room.entities.Note
import com.uckmhnds.averroes.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteMapper @Inject constructor() {

    fun mapNoteEntity(noteFlow: Flow<List<Note>>): LiveData<List<NoteModel>>{

        return noteFlow.asLiveData().map { noteList ->  noteList.map {noteEntity ->

            NoteModel(
                id          = noteEntity.id,
                title       = noteEntity.title,
                text        = noteEntity.text,
                date        = noteEntity.date,
                category    = noteEntity.category

            )
        }}
    }

    fun mapNoteModel(noteModel: NoteModel): Note {

        Log.i("mapNoteModelByValue", noteModel.id.toString())

        return Note(
            id          = noteModel.id,
            title       = noteModel.title!!,
            text        = noteModel.text!!,
            date        = noteModel.date!!,
            category    = noteModel.category!!
        )
    }

}