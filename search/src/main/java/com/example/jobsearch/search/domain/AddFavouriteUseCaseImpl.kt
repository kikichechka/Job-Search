package com.example.jobsearch.search.domain

import com.example.jobsearch.search.domain.model.Vacancy
import javax.inject.Inject

class AddFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): AddFavouriteUseCase {
    override suspend fun add(vacancy: Vacancy) {
        dataRepository.addFavourite(vacancy)
    }
}
