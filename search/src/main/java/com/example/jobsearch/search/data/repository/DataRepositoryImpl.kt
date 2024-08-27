package com.example.jobsearch.search.data.repository

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.datasource.VacanciesLocalDataSource
import com.example.jobsearch.search.data.dto.VacancyDto
import com.example.jobsearch.search.data.dto.mapToDb
import com.example.jobsearch.search.data.dto.mapToDto
import com.example.jobsearch.search.data.dto.mapToModel
import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.jobsearch.search.domain.model.VacancyModel
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource,
    private val vacanciesLocalDataSource: VacanciesLocalDataSource
) : DataRepository {
    override suspend fun getAllData(): AllDataModel? {
        val allData = offersAndVacanciesRemoteDataSource.getData()
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

    override suspend fun deleteFavourite(vacancyModel: VacancyModel) {
        vacanciesLocalDataSource.deleteData(vacancyModel.mapToDto().mapToDb())
    }

    override suspend fun addFavourite(vacancyModel: VacancyModel) {
        vacanciesLocalDataSource.insertData(vacancyModel.mapToDto().mapToDb())
    }
}
