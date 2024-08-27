package com.example.jobsearch.details.data.dto

import com.example.jobsearch.details.domain.model.SalaryModel

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
