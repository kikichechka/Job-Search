package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.Address
import com.example.jobsearch.favourites.domain.model.Experience
import com.example.jobsearch.favourites.domain.model.Salary
import com.example.jobsearch.favourites.domain.model.Vacancy

class VacancyDto(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)

fun VacancyDto.mapToModel(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = address,
        company = company,
        experience = experience,
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary,
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}
