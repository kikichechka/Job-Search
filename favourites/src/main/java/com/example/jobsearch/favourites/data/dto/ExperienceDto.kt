package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.Experience

class ExperienceDto(
    val previewText: String,
    val text: String
)

fun ExperienceDto.mapToModel(): Experience {
    return Experience(
        previewText = previewText,
        text = text
    )
}
