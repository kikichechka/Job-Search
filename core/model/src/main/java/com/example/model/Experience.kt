package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Experience(
    val previewText: String,
    val text: String
): Parcelable
