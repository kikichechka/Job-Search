package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.Experience
import com.example.jobsearch.favourites.domain.model.Salary

class SalaryDto(
    val short: String?,
    val full: String
)

fun SalaryDto.mapToModel(): Salary {
    return Salary(
        short = short,
        full = full
    )
}
