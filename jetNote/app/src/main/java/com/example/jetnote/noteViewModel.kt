package com.example.jetnote

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.domain.Note

class noteViewModel : ViewModel() {

    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(getAllNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> = noteList
}