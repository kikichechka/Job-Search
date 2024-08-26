package com.example.jobsearch.search.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.jobsearch.search.data.dto.AddressDto
import com.example.jobsearch.search.data.dto.ExperienceDto
import com.example.jobsearch.search.data.dto.SalaryDto
import com.example.jobsearch.search.data.dto.VacancyDto

data class VacancyDatabase(
    @Embedded
    val vacancyInfoDb: VacancyInfoDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "vacancy_id"
    )
    val address: AddressDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "vacancy_id"
    )
    val experience: ExperienceDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "vacancy_id"
    )
    val salary: SalaryDb
)

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
