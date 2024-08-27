package com.example.jobsearch.search.data.datasource

import com.example.database.VacancyDao
import com.example.database.model.VacancyDatabase
import javax.inject.Inject

class VacanciesLocalDataSource @Inject constructor(
    private val vacancyDao: VacancyDao
) {
    suspend fun getAllData(): List<VacancyDatabase> {
        return vacancyDao.getAll()
    }

    suspend fun insertData(vacancyDatabase: VacancyDatabase) {
        vacancyDao.insert(vacancyDatabase)
    }

    suspend fun deleteData(vacancyDatabase: VacancyDatabase) {
        vacancyDao.delete(vacancyDatabase)
    }
}
