package com.example.jobsearch.details.domain.usecase

import com.example.jobsearch.details.domain.model.VacancyModel

interface DeleteFavouriteVacancyUseCase {
    suspend fun delete(vacancyModel: VacancyModel) : Int
}
