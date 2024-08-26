package com.example.jobsearch.search.data.db.converters

import androidx.room.TypeConverter
import java.time.LocalDate

class ConvertersListString {
    @TypeConverter
    fun fromString(list: List<String>): String {
        return list.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun toList(str: String): List<String> {
        return str.split(SEPARATOR)
    }

    companion object{
        private const val SEPARATOR = "||"
    }
}