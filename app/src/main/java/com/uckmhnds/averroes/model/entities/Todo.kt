package com.uckmhnds.averroes.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val text: String,

    val date: String,

    var done: Boolean
)
