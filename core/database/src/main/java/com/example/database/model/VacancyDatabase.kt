package com.example.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class VacancyDatabase (
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