package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.TextButton

class TextButton (
    val text: String
)

fun TextButton.mapToUi(): com.example.jobsearch.search.presentation.uimodel.TextButton {
    return com.example.jobsearch.search.presentation.uimodel.TextButton(
        text = text
    )
}
