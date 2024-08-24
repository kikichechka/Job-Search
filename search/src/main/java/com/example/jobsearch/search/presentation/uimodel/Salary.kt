package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.Salary

class Salary(
    val short: String?,
    val full: String
)

fun Salary.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Salary {
    return com.example.jobsearch.search.presentation.uimodel.Salary(
        short = short,
        full = full
    )
}
