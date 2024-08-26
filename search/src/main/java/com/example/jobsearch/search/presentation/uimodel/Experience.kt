package com.example.jobsearch.search.presentation.uimodel

import android.os.Parcelable
import com.example.jobsearch.search.domain.model.Experience
import kotlinx.parcelize.Parcelize

@Parcelize
class Experience(
    val previewText: String,
    val text: String
):Parcelable

fun Experience.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Experience {
    return com.example.jobsearch.search.presentation.uimodel.Experience(
        previewText = previewText,
        text = text
    )
}
