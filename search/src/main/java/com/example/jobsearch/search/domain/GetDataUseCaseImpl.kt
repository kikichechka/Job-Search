package com.example.jobsearch.search.domain

import com.example.jobsearch.search.domain.model.AllData
import javax.inject.Inject

class GetDataUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): GetDataUseCase {
    override suspend fun get(): AllData? {
        return dataRepository.getData()
    }
}
