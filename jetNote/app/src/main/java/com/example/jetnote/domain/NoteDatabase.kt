package com.example.jetnote.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.jetnote.util.dataConvert
import com.example.jetnote.util.uuidConverter


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(dataConvert::class)
abstract class NoteDatabase:RoomDatabase()
{

    abstract fun noteDao():NoteDatabaseDao

}