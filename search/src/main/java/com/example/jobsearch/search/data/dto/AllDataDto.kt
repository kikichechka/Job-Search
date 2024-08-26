package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.AllData

data class AllDataDto(
    val offers: List<OfferDto>,
    var vacancies: List<VacancyDto>
)

fun AllDataDto.mapToModel(): AllData {
    return AllData(
        offers = offers.map { it.mapToModel() },
        vacancies = vacancies.map { it.mapToModel() }
    )
}
