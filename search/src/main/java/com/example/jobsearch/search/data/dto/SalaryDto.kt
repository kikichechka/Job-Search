package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.Salary

data class SalaryDto(
    val short: String?,
    val full: String
)

fun SalaryDto.mapToModel(): Salary {
    return Salary(
        short = short,
        full = full
    )
}
