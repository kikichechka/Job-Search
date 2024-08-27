package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.model.AllDataModel

interface GetDataUseCase {
    suspend fun get(): AllDataModel?
}
