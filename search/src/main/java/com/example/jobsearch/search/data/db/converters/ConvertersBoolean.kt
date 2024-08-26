package com.example.jobsearch.search.data.db.converters

import androidx.room.TypeConverter

class ConvertersBoolean {
    @TypeConverter
    fun fromInt(isBoolean: Boolean): Int {
        return if (isBoolean) 1 else 0
    }

    @TypeConverter
    fun toBoolean(int: Int): Boolean {
        return int == 1
    }
}
