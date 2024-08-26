package com.example.jobsearch.search.presentation.uimodel

import android.os.Parcelable
import com.example.jobsearch.search.domain.model.Vacancy
import kotlinx.parcelize.Parcelize

@Parcelize
class Vacancy (
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
): Parcelable

fun Vacancy.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Vacancy {
    return Vacancy(
        id = id,
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

fun com.example.jobsearch.search.presentation.uimodel.Vacancy.mapToDomain(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = com.example.jobsearch.search.domain.model.Address(
            town = address.town,
            street = address.street,
            house = address.street
        ),
        company = company,
        experience = com.example.jobsearch.search.domain.model.Experience(
            previewText = experience.previewText,
            text = experience.text
        ),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = com.example.jobsearch.search.domain.model.Salary(
            short = salary.short,
            full = salary.full
        ),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}
