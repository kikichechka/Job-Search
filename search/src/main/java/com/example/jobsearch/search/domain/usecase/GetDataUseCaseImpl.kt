package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.AllData
import javax.inject.Inject

class GetDataUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): GetDataUseCase {
    override suspend fun get(): AllData? {
        return dataRepository.getAllData()
    }
}
