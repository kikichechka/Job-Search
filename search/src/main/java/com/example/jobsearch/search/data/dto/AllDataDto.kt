package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.network.dto.AllDataRemote

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

fun AllDataRemote.mapToDto(): AllDataDto {
    return AllDataDto(
        offers = offers.map { it.mapToDto() },
        vacancies = vacancies.map { it.mapToDto() }
    )
}
