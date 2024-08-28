package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.TextButtonModel

data class TextButtonDto(
    val text: String
)

fun TextButtonDto.mapToModel(): TextButtonModel {
    return TextButtonModel(
        text = text
    )
}
