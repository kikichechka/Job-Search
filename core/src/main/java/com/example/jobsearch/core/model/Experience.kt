package com.example.jobsearch.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Experience(
    val previewText: String,
    val text: String
):Parcelable