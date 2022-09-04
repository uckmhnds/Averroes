package com.uckmhnds.averroes.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uckmhnds.averroes.model.dao.NoteDao
import com.uckmhnds.averroes.model.dao.TodoDao
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.model.entities.Todo

@Database(entities = [Note::class, Todo::class], version = 1, exportSchema = false)
public abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun todoDao(): TodoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}