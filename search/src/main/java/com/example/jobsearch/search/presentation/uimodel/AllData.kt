package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.jobsearch.search.presentation.fragment.search.mapToUi
import com.example.model.Vacancy

class AllData (
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

fun AllDataModel.mapToUi(): AllData {
    return AllData(
        offers = offerModels.map { it.mapToUi() },
        vacancies = vacancies.map { it.mapToUi() }
    )
}
