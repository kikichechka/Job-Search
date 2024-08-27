package com.example.jobsearch.details.domain.repository

import com.example.jobsearch.details.domain.model.VacancyModel

interface FavouriteRepository {
    suspend fun deleteFavourite(vacancyModel: VacancyModel) : Int
    suspend fun addFavourite(vacancyModel: VacancyModel) : Int
}
