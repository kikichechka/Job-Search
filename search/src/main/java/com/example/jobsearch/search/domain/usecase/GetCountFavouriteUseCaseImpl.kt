package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import javax.inject.Inject

class GetCountFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
) : GetCountFavouriteUseCase {
    override suspend fun get(): Int {
        return dataRepository.getCountFavouriteVacancies()
    }
}
