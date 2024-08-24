package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.Experience

class Experience(
    val previewText: String,
    val text: String
)

fun Experience.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Experience {
    return com.example.jobsearch.search.presentation.uimodel.Experience(
        previewText = previewText,
        text = text
    )
}
