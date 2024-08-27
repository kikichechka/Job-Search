package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.repository.DataRepository
import com.example.jobsearch.search.domain.model.VacancyModel
import javax.inject.Inject

class AddFavouriteUseCaseImpl @Inject constructor(
    private val dataRepository: DataRepository
): AddFavouriteUseCase {
    override suspend fun add(vacancyModel: VacancyModel) {
        dataRepository.addFavourite(vacancyModel)
    }
}
