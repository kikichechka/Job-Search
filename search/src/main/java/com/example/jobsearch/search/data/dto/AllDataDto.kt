package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.AllDataModel

data class AllDataDto(
    val offers: List<OfferDto>,
    var vacancies: List<VacancyDto>
)

fun AllDataDto.mapToModel(): AllDataModel {
    return AllDataModel(
        offerModels = offers.map { it.mapToModel() },
        vacancies = vacancies.map { it.mapToModel() }
    )
}
