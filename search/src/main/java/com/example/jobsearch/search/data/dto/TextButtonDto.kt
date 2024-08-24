package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.TextButton

data class TextButtonDto(
    val text: String
)

fun TextButtonDto.mapToModel(): TextButton {
    return TextButton(
        text = text
    )
}
