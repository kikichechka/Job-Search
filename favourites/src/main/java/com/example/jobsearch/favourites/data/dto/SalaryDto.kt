package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.SalaryModel

class SalaryDto(
    val short: String?,
    val full: String
)

fun SalaryDto.mapToModel(): SalaryModel {
    return SalaryModel(
        short = short,
        full = full
    )
}

fun SalaryModel.mapToDto(): SalaryDto {
    return SalaryDto(
        short = short,
        full = full
    )
}
