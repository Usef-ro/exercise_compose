package com.example.jetnote.repository

import com.example.jetnote.domain.Note
import com.example.jetnote.domain.NoteDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class noteRepository @Inject constructor(
    val noteDatabaseDao: NoteDatabaseDao
) {
    suspend fun addNote(note:Note)=noteDatabaseDao.insert(note)
    suspend fun updateNote(note:Note)=noteDatabaseDao.update(note)
    suspend fun deleteNote(note:Note)=noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes()=noteDatabaseDao.deleteAll()

     fun getAllNotes():Flow<List<Note>> =noteDatabaseDao.getNotes()
        .flowOn(Dispatchers.IO).conflate()

//    suspend fun getNote(note:Note):Note = noteDatabaseDao.
}