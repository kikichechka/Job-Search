package com.example.jobsearch.search.data.repository

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.datasource.VacanciesLocalDataSource
import com.example.jobsearch.search.data.db.model.mapToDto
import com.example.jobsearch.search.data.dto.VacancyDto
import com.example.jobsearch.search.data.dto.mapToDb
import com.example.jobsearch.search.data.dto.mapToDto
import com.example.jobsearch.search.data.dto.mapToModel
import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.AllData
import com.example.jobsearch.search.domain.model.Vacancy
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource,
    private val vacanciesLocalDataSource: VacanciesLocalDataSource
) : DataRepository {
    override suspend fun getAllData(): AllData? {
        val allData = offersAndVacanciesRemoteDataSource.getData().body()
        allData?.vacancies?.let { allVacancies ->
            val favouriteVacanciesInLocalDataSource =
                addFavouriteVacanciesFromRemoteInLocal(allVacancies)
            addFavouriteVacanciesFromLocalInRemote(
                favouriteVacanciesInLocalDataSource,
                allVacancies
            )
            allData.vacancies = allVacancies
        }
        return allData?.mapToModel()
    }

    private fun addFavouriteVacanciesFromLocalInRemote(
        favouriteVacanciesInLocalDataSource: List<VacancyDto>,
        allVacancies: List<VacancyDto>
    ) {
        for (favourite in favouriteVacanciesInLocalDataSource) {
            for (all in allVacancies) {
                if (favourite.id == all.id)
                    all.isFavorite = true
            }
        }
    }

    private suspend fun addFavouriteVacanciesFromRemoteInLocal(
        allVacancies: List<VacancyDto>?,
    ): List<VacancyDto> {
        val favouriteVacanciesInLocalDataSource =
            vacanciesLocalDataSource.getAllData().map { it.mapToDto() }
        allVacancies?.let {
            for (favouriteVacancy in it) {
                if (favouriteVacancy.isFavorite)
                    vacanciesLocalDataSource.insertData(favouriteVacancy.mapToDb())
            }
        }
        return favouriteVacanciesInLocalDataSource
    }

    override suspend fun getCountFavouriteVacancies(): Int {
        return vacanciesLocalDataSource.getAllData().size
    }

    override suspend fun deleteFavourite(vacancy: Vacancy) {
        vacanciesLocalDataSource.deleteData(vacancy.mapToDto().mapToDb())
    }

    override suspend fun addFavourite(vacancy: Vacancy) {
        vacanciesLocalDataSource.insertData(vacancy.mapToDto().mapToDb())
    }
}
