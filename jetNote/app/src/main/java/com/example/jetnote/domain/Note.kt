package com.example.jetnote.domain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "note_tb")
data class Note @RequiresApi(Build.VERSION_CODES.O) constructor(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(
        name = "note_title"
    )

    val title: String,
    @ColumnInfo(
        name = "note_description"
    )
    val description: String,

    @ColumnInfo(
        name = "note_entry_date"
    )
    val entry: java.util.Date = Date.from(Instant.now())
)
