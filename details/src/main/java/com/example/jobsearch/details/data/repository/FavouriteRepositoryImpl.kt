package com.example.jobsearch.details.data.repository

import com.example.jobsearch.details.data.datasource.FavouriteVacanciesLocalDataSource
import com.example.jobsearch.details.data.dto.mapToDto
import com.example.jobsearch.details.domain.model.VacancyModel
import com.example.jobsearch.details.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteVacanciesLocalDataSource: FavouriteVacanciesLocalDataSource
): FavouriteRepository {
    override suspend fun deleteFavourite(vacancyModel: VacancyModel) : Int {
        return favouriteVacanciesLocalDataSource.deleteFavouriteVacancy(vacancyModel.mapToDto())
    }

    override suspend fun addFavourite(vacancyModel: VacancyModel): Int {
        return favouriteVacanciesLocalDataSource.addFavouriteVacancy(vacancyModel.mapToDto())
    }
}
