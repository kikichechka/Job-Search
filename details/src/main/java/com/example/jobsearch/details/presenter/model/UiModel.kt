package com.example.jobsearch.details.presenter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UiModel (
    val titleVacancy: String,
    val question: String?
) : Parcelable
