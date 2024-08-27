package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.jobsearch.search.domain.model.SalaryModel
import com.example.network.dto.AllDataRemote
import com.example.network.dto.SalaryRemote

data class SalaryDto(
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

fun SalaryRemote.mapToDto(): SalaryDto {
    return SalaryDto(
        short = short,
        full = full
    )
}
