package com.example.jobsearch.details.data.datasource

import com.example.database.VacancyDao
import com.example.jobsearch.details.data.dto.VacancyDto
import com.example.jobsearch.details.data.dto.mapToDb
import javax.inject.Inject

class FavouriteVacanciesLocalDataSource @Inject constructor(
    private val vacancyDao: VacancyDao
) {
    suspend fun deleteFavouriteVacancy(vacancyDto: VacancyDto) : Int {
        vacancyDao.delete(vacancyDto.mapToDb())
        return vacancyDao.getAll().size
    }

    suspend fun addFavouriteVacancy(vacancyDto: VacancyDto): Int {
        vacancyDao.insert(vacancyDto.mapToDb())
        return vacancyDao.getAll().size
    }
}
