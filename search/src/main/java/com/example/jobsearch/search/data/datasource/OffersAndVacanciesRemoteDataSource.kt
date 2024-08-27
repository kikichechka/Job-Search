package com.example.jobsearch.search.data.datasource

import com.example.jobsearch.search.data.dto.AllDataDto
import com.example.jobsearch.search.data.dto.mapToDto
import com.example.network.MyRetrofit
import javax.inject.Inject

class OffersAndVacanciesRemoteDataSource @Inject constructor(
    private val myRetrofit: MyRetrofit
) {
    suspend fun getData(): AllDataDto? {
        return myRetrofit.searchData.getOffers().body()?.mapToDto()
    }
}
