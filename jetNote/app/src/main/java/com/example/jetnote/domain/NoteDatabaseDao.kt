package com.example.jetnote.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM note_tb")
    fun getNotes():
            Flow<List<Note>>

    @Query("SELECT * FROM note_tb where id=:id")
    suspend fun getNotesById(id:String):List<Note>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        note:Note
    )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    @Query("DELETE from note_tb")
    suspend  fun deleteAll()

    @Delete
   suspend fun deleteNote(note:Note)

}