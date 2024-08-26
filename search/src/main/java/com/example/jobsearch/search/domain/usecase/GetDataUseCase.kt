package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.model.AllData

interface GetDataUseCase {
    suspend fun get(): AllData?
}
