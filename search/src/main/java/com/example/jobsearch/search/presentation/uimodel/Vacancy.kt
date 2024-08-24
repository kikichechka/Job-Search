package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.Vacancy

class Vacancy (
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)

fun Vacancy.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Vacancy {
    return Vacancy(
        lookingNumber = lookingNumber,
        title = title,
        address = address.mapToUi(),
        company = company,
        experience = experience.mapToUi(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.mapToUi(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}
