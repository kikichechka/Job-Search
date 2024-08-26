package com.example.jobsearch.search.domain

import javax.inject.Inject

class GetCountFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
) : GetCountFavouriteUseCase {
    override suspend fun get(): Int {
        return dataRepository.getCountFavouriteVacancies()
    }
}
