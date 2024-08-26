package com.example.jobsearch.search.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

fun Salary.mapToDto(): SalaryDto {
    return SalaryDto(
        short = short,
        full = full
    )
}
