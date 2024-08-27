package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.model.VacancyModel

interface AddFavouriteUseCase {
    suspend fun add(vacancyModel: VacancyModel)
}
