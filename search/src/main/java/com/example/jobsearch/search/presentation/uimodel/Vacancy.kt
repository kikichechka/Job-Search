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
