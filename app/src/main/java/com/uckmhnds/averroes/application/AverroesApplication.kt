package com.uckmhnds.averroes.application

import android.app.Application
import com.uckmhnds.averroes.model.database.AppDatabase
import com.uckmhnds.averroes.model.repository.NoteRepository
import com.uckmhnds.averroes.model.repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AverroesApplication: Application() {    // No need to cancel this scope as it'll be torn down with the process

    val applicationScope            = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val noteDatabase by lazy { NoteRoomDatabase.getDatabase(this@AverroesApplication) }
//    val noteRepository by lazy { NoteRepository(noteDatabase.noteDao()) }
//
//    val todoDatabase by lazy { TodoRoomDatabase.getDatabase(this@AverroesApplication) }
//    val todoRepository by lazy { TodoRepository(todoDatabase.todoDao()) }

    val appDatabase by lazy { AppDatabase.getDatabase(this@AverroesApplication) }

    val noteRepository by lazy { NoteRepository(appDatabase.noteDao()) }
    val todoRepository by lazy { TodoRepository(appDatabase.todoDao()) }
}