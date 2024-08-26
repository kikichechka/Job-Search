package com.example.jobsearch.search.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jobsearch.search.domain.model.Experience

data class ExperienceDto(
    val previewText: String,
    val text: String
)

fun ExperienceDto.mapToModel(): Experience {
    return Experience(
        previewText = previewText,
        text = text
    )
}

fun Experience.mapToDto(): ExperienceDto {
    return ExperienceDto(
        previewText = previewText,
        text = text
    )
}
