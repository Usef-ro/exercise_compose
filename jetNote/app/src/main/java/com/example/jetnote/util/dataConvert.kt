package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.Date

class dataConvert {
    @TypeConverter
    fun timeStampFromData(
        date:Date?
    ):Long?{
        return date?.time?.toLong()
    }

    @TypeConverter
    fun dateFromTimeStamp(
        timeStamp:Long?
    ):Date?{
        return Date(timeStamp!!)
    }

}