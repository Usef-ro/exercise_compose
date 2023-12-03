package com.example.jetnote

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.domain.Note
import com.example.jetnote.repository.noteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class noteViewModel @Inject constructor(
    val repository: noteRepository
): ViewModel() {

    val _noteList=MutableStateFlow<List<Note>> (emptyList())
    var noteList=_noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect(){listofNote->
                    if(listofNote.isEmpty()){
                        Log.e("viewmodel", "list of note : null ", )
                    }else{
                        _noteList.value=listofNote
                    }
                }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

  suspend  fun removeNote(note: Note) {
       repository.deleteNote(note)
    }

//  suspend  fun getAllNotes(): List<Note> = viewModelScope.launch { repository.getAllNotes() }
}