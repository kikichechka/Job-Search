package com.example.network.dto

class VacancyRemote (
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: AddressRemote,
    val company: String,
    val experience: ExperienceRemote,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: SalaryRemote,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)
