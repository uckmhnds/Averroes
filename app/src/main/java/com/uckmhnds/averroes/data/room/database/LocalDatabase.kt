package com.uckmhnds.averroes.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uckmhnds.averroes.data.room.dao.NoteDao
import com.uckmhnds.averroes.data.room.dao.TodoDao
import com.uckmhnds.averroes.data.room.entities.Note
import com.uckmhnds.averroes.data.room.entities.Todo

@Database(entities = [Note::class, Todo::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun todoDao(): TodoDao

//    class Callback @Inject constructor(
//        private val database: Provider<LocalDatabase>,
//        @ApplicationScope private val applicationScope: CoroutineScope
//    ) : RoomDatabase.Callback()


    companion object {
        fun create(context: Context): LocalDatabase {

            return Room.databaseBuilder(
                context,
                LocalDatabase::class.java,
                "averroes_database"
            ).build()
        }
    }

}