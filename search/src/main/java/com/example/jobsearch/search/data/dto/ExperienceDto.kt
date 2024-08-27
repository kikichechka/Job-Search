package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.ExperienceModel
import com.example.network.dto.ExperienceRemote
import com.example.network.dto.OfferRemote

data class ExperienceDto(
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

fun ExperienceRemote.mapToDto(): ExperienceDto {
    return ExperienceDto(
        previewText = previewText,
        text = text
    )
}
