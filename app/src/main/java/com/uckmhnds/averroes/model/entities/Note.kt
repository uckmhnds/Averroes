package com.uckmhnds.averroes.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")

data class Note(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    var title:String,

    var text:String,

    var date: String,

    var category: String,
)
