package com.uckmhnds.averroes.di

import android.content.Context
import com.uckmhnds.averroes.data.room.dao.NoteDao
import com.uckmhnds.averroes.data.room.dao.TodoDao
import com.uckmhnds.averroes.data.room.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(application: Application, callback: LocalDatabase.Callback): LocalDatabase{
//        return Room.databaseBuilder(application, LocalDatabase::class.java, "averroes_database")
//            .fallbackToDestructiveMigration()
//            .addCallback(callback)
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun provideNoteRepository(noteRepository: NoteRepositoryImp): NoteRepository =
//        noteRepository

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase =
        LocalDatabase.create(context)

    @Provides
    fun provideNoteDao(database: LocalDatabase): NoteDao{
        return database.noteDao()
    }

    @Provides
    fun provideTodoDao(database: LocalDatabase): TodoDao {
        return database.todoDao()
    }
}

