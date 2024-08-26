package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.Vacancy
import javax.inject.Inject

class DeleteFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): DeleteFavouriteUseCase {
    override suspend fun delete(vacancy: Vacancy) {
        dataRepository.deleteFavourite(vacancy)
    }
}
