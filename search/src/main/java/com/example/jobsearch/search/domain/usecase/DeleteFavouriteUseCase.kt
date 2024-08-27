package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.model.VacancyModel

interface DeleteFavouriteUseCase {
    suspend fun delete(vacancyModel: VacancyModel)
}
