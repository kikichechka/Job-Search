package com.example.jobsearch.favourites.data.datasource

import com.example.database.VacancyDao
import com.example.database.model.VacancyDatabase
import com.example.jobsearch.favourites.data.dto.VacancyDto
import com.example.jobsearch.favourites.data.dto.mapToDb
import com.example.jobsearch.favourites.data.dto.mapToDto
import com.example.jobsearch.favourites.data.dto.mapToModel
import javax.inject.Inject

class FavouriteVacanciesLocalDataSource @Inject constructor(
    private val vacancyDao: VacancyDao
) {
    suspend fun getAllData(): List<VacancyDto> {
        return vacancyDao.getAll().map { it.mapToDto() }
    }

    suspend fun deleteVacancy(vacancyDto: VacancyDto) {
        return vacancyDao.delete(vacancyDto.mapToDb())
    }
}