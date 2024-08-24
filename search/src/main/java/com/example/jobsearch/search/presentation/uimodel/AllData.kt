package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.data.dto.AllDataDto
import com.example.jobsearch.search.data.dto.mapToModel
import com.example.jobsearch.search.domain.model.AllData

class AllData (
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

fun AllData.mapToUi(): com.example.jobsearch.search.presentation.uimodel.AllData {
    return AllData(
        offers = offers.map { it.mapToUi() },
        vacancies = vacancies.map { it.mapToUi() }
    )
}
