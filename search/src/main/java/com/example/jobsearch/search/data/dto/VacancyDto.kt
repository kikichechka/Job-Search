package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.Vacancy

data class VacancyDto(
    val lookingNumber: Int?,
    val title: String,
    val address: AddressDto,
    val company: String,
    val experience: ExperienceDto,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDto,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)

fun VacancyDto.mapToModel(): Vacancy {
    return Vacancy(
        lookingNumber = lookingNumber,
        title = title,
        address = address.mapToModel(),
        company = company,
        experience = experience.mapToModel(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.mapToModel(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}
