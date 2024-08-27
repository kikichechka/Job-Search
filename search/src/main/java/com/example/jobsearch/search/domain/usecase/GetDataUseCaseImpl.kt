package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.AllDataModel
import javax.inject.Inject

class GetDataUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): GetDataUseCase {
    override suspend fun get(): AllDataModel? {
        return dataRepository.getAllData()
    }
}
