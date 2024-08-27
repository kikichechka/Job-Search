package com.example.jobsearch.favourites.domain.model

class VacancyModel (
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val addressModel: AddressModel,
    val company: String,
    val experience: ExperienceModel,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salaryModel: SalaryModel,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)