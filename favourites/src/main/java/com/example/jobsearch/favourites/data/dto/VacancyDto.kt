package com.example.jobsearch.favourites.data.dto

import com.example.database.model.AddressDb
import com.example.database.model.ExperienceDb
import com.example.database.model.SalaryDb
import com.example.database.model.VacancyDatabase
import com.example.database.model.VacancyInfoDb
import com.example.jobsearch.favourites.domain.model.VacancyModel

class VacancyDto(
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

fun VacancyDto.mapToModel(): VacancyModel {
    return VacancyModel(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        addressModel = address.mapToModel(),
        company = company,
        experience = experience.mapToModel(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salaryModel = salary.mapToModel(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )
}

fun VacancyDto.mapToDb(): VacancyDatabase {
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
            questions = questions
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

fun VacancyDatabase.mapToDto(): VacancyDto {
    return VacancyDto(
        id = vacancyInfoDb.id,
        lookingNumber = vacancyInfoDb.lookingNumber,
        title = vacancyInfoDb.title,
        company = vacancyInfoDb.company,
        publishedDate = vacancyInfoDb.publishedDate,
        isFavorite = vacancyInfoDb.isFavorite,
        schedules = vacancyInfoDb.schedules,
        appliedNumber = vacancyInfoDb.appliedNumber,
        description = vacancyInfoDb.description,
        responsibilities = vacancyInfoDb.responsibilities,
        questions = vacancyInfoDb.questions,
        address = AddressDto(
            town = address.town,
            street = address.street,
            house = address.house
        ),
        experience = ExperienceDto(
            previewText = experience.previewText,
            text = experience.text
        ),
        salary = SalaryDto(
            short = salary.short,
            full = salary.full
        )
    )
}

fun VacancyModel.mapToDto(): VacancyDto {
    return VacancyDto(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = addressModel.mapToDto(),
        company = company,
        experience = experience.mapToDto(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salaryModel.mapToDto(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}
