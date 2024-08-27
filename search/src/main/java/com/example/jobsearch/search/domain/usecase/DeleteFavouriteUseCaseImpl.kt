package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.VacancyModel
import javax.inject.Inject

class DeleteFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): DeleteFavouriteUseCase {
    override suspend fun delete(vacancyModel: VacancyModel) {
        dataRepository.deleteFavourite(vacancyModel)
    }
}
