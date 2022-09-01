package com.uckmhnds.averroes.application

import android.app.Application
import com.uckmhnds.averroes.model.database.NoteRoomDatabase
import com.uckmhnds.averroes.model.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AverroesApplication: Application() {    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NoteRoomDatabase.getDatabase(this@AverroesApplication) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}