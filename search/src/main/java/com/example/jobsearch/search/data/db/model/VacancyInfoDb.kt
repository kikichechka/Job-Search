package com.example.jobsearch.search.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class VacancyInfoDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "looking_number")
    val lookingNumber: Int?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "published_date")
    val publishedDate: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "schedules")
    val schedules: List<String>,
    @ColumnInfo(name = "applied_number")
    val appliedNumber: Int?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "responsibilities")
    val responsibilities: String,
    @ColumnInfo(name = "questions")
    val questions: List<String>,
)
