package com.example.jetnote.di

import android.content.Context
import androidx.room.Room
import com.example.jetnote.domain.NoteDatabase
import com.example.jetnote.domain.NoteDatabaseDao
import com.example.jetnote.util.dataConvert
import com.example.jetnote.util.uuidConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object appModule {


    @Singleton
    @Provides
    fun provideNotesDao(
        noteDatabase: NoteDatabase
    ):NoteDatabaseDao=noteDatabase.noteDao()


    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ):NoteDatabase= Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note_db"

    ).fallbackToDestructiveMigration()
//        .addTypeConverter(uuidConverter::class)
        .build()
}