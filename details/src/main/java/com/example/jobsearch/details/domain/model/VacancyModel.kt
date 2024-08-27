package com.example.jobsearch.details.domain.model

import com.example.jobsearch.details.domain.model.ExperienceModel
import com.example.jobsearch.details.domain.model.SalaryModel

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