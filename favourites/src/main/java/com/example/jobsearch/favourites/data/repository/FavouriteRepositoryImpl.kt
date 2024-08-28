package com.example.jobsearch.favourites.data.repository

import com.example.jobsearch.favourites.data.datasource.FavouriteVacanciesLocalDataSource
import com.example.jobsearch.favourites.data.dto.mapToDto
import com.example.jobsearch.favourites.data.dto.mapToModel
import com.example.jobsearch.favourites.domain.model.VacancyModel
import com.example.jobsearch.favourites.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteVacanciesLocalDataSource: FavouriteVacanciesLocalDataSource
): FavouriteRepository {
    override suspend fun get(): List<VacancyModel> {
        return favouriteVacanciesLocalDataSource.getAllData().map { it.mapToModel() }
    }

    override suspend fun delete(vacancyModel: VacancyModel) {
        favouriteVacanciesLocalDataSource.deleteVacancy(vacancyModel.mapToDto())
    }
}
