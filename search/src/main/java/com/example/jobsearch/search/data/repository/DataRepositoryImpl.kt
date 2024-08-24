package com.example.jobsearch.search.data.repository

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.dto.mapToModel
import com.example.jobsearch.search.domain.DataRepository
import com.example.jobsearch.search.domain.model.AllData
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource
) : DataRepository {
    override suspend fun getData(): AllData? {
        return offersAndVacanciesRemoteDataSource.getData().body()?.mapToModel()
    }
}
