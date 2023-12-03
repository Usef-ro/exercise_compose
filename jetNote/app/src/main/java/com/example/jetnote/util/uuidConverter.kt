package com.example.jetnote.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.UUID

//@ProvidedTypeConverter

class uuidConverter {

    @TypeConverter
    fun fromUUID(uuid: String?): String? {
        return uuid?.toString()
    }
    @TypeConverter
    fun uuidFromString(string:String?): UUID?{
        return UUID.fromString(string)
    }

}