package com.example.jobsearch.favourites.domain.repository

import com.example.jobsearch.favourites.domain.model.VacancyModel

interface FavouriteRepository {
    suspend fun get(): List<VacancyModel>
    suspend fun delete(vacancyModel: VacancyModel)
}
