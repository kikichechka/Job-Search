package com.example.jobsearch.details.data.dto

import com.example.jobsearch.details.domain.model.ExperienceModel

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
