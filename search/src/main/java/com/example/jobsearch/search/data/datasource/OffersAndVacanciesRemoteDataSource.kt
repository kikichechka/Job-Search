package com.example.jobsearch.search.data.datasource

import com.example.jobsearch.search.data.MyRetrofit
import com.example.jobsearch.search.data.dto.AllDataDto
import retrofit2.Response
import javax.inject.Inject

class OffersAndVacanciesRemoteDataSource @Inject constructor(
    private val myRetrofit: MyRetrofit
) {
    suspend fun getData(): Response<AllDataDto> {
        return myRetrofit.searchData.getOffers()
    }
}