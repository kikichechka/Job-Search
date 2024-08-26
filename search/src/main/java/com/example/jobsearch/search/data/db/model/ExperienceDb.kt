package com.example.jobsearch.search.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experience")
data class ExperienceDb(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    @ColumnInfo(name = "preview_text")
    val previewText: String,
    @ColumnInfo(name = "text")
    val text: String
)
