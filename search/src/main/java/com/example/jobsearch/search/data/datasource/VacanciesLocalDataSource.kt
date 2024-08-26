package com.example.jobsearch.search.data.datasource

import com.example.jobsearch.search.data.db.VacancyDao
import com.example.jobsearch.search.data.db.model.VacancyDatabase
import kotlinx.coroutines.delay
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
