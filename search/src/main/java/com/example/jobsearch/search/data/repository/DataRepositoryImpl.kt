package com.example.jobsearch.search.data.repository

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.datasource.VacanciesLocalDataSource
import com.example.jobsearch.search.data.db.model.mapToDto
import com.example.jobsearch.search.data.dto.mapToDb
import com.example.jobsearch.search.data.dto.mapToDto
import com.example.jobsearch.search.data.dto.mapToModel
import com.example.jobsearch.search.domain.DataRepository
import com.example.jobsearch.search.domain.model.AllData
import com.example.jobsearch.search.domain.model.Vacancy
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource,
    private val vacanciesLocalDataSource: VacanciesLocalDataSource
) : DataRepository {
    override suspend fun getAllData(): AllData? {
        val allData = offersAndVacanciesRemoteDataSource.getData().body()
        val allVacancies = allData?.vacancies
        val favouriteVacanciesInLocalDataSource =
            vacanciesLocalDataSource.getAllData().map { it.mapToDto() }
        allVacancies?.let {
            for (favouriteVacancy in it) {
                if (favouriteVacancy.isFavorite)
                    vacanciesLocalDataSource.insertData(favouriteVacancy.mapToDb())
            }
        }
        for (favourite in favouriteVacanciesInLocalDataSource) {
            if (allVacancies != null) {
                for (all in allVacancies) {
                    if (favourite.id == all.id)
                        all.isFavorite = true
                }
            }
        }
        if (allVacancies != null) {
            allData.vacancies = allVacancies
        }
        return allData?.mapToModel()
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
