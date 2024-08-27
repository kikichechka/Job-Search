package com.example.jobsearch.details.domain.usecase

import com.example.jobsearch.details.domain.model.VacancyModel

interface AddFavouriteVacancyUseCase {
    suspend fun add(vacancyModel: VacancyModel) : Int
}
