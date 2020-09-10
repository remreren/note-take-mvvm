package com.bukonudakonusalim.takenotes.utils

import androidx.room.TypeConverter
import org.joda.time.DateTime

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun toDateTime(timeStamp: Long?): DateTime? {
        return DateTime(timeStamp)
    }

    @TypeConverter
    @JvmStatic
    fun toTimeStamp(dateTime: DateTime?): Long? {
        return dateTime?.millis
    }
}