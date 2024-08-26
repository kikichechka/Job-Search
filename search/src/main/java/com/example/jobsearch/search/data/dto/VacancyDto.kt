package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.data.db.model.AddressDb
import com.example.jobsearch.search.data.db.model.ExperienceDb
import com.example.jobsearch.search.data.db.model.SalaryDb
import com.example.jobsearch.search.data.db.model.VacancyDatabase
import com.example.jobsearch.search.data.db.model.VacancyInfoDb
import com.example.jobsearch.search.domain.model.Vacancy
import com.example.jobsearch.search.presentation.uimodel.mapToUi

data class VacancyDto(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: AddressDto,
    val company: String,
    val experience: ExperienceDto,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: SalaryDto,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
)

fun VacancyDto.mapToDb() : VacancyDatabase {
    return VacancyDatabase(
        VacancyInfoDb(
            id = id,
            lookingNumber = lookingNumber,
            title = title,
            company = company,
            publishedDate = publishedDate,
            isFavorite = isFavorite,
            schedules = schedules,
            appliedNumber = appliedNumber,
            description = description,
            responsibilities = responsibilities,
            questions = questions,
        ),
        address = AddressDb(
            vacancyId = id,
            town = address.town,
            street = address.street,
            house = address.house
        ),
        experience = ExperienceDb(
            vacancyId = id,
            previewText = experience.previewText,
            text = experience.text
        ),
        salary = SalaryDb(
            vacancyId = id,
            short = salary.short,
            full = salary.full
        )
    )
}

fun VacancyDto.mapToModel(): Vacancy {
    return Vacancy(
        id = id,
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

fun Vacancy.mapToDto(): VacancyDto {
    return VacancyDto(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = address.mapToDto(),
        company = company,
        experience = experience.mapToDto(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.mapToDto(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}

