package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.model.VacancyModel

interface GetFavouriteUseCase {
    suspend fun getData(): List<VacancyModel>
}
