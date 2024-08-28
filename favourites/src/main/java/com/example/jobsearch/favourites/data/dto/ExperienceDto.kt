package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.ExperienceModel

class ExperienceDto(
    val previewText: String,
    val text: String
)

fun ExperienceDto.mapToModel(): ExperienceModel {
    return ExperienceModel(
        previewText = previewText,
        text = text
    )
}

fun ExperienceModel.mapToDto(): ExperienceDto {
    return ExperienceDto(
        previewText = previewText,
        text = text
    )
}
