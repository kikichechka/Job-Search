package com.example.jobsearch.search.presentation.uimodel

import android.os.Parcelable
import com.example.jobsearch.search.domain.model.Salary
import kotlinx.parcelize.Parcelize

@Parcelize
class Salary(
    val short: String?,
    val full: String
):Parcelable

fun Salary.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Salary {
    return com.example.jobsearch.search.presentation.uimodel.Salary(
        short = short,
        full = full
    )
}
