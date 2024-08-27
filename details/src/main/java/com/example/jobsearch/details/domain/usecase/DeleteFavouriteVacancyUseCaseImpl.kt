package com.example.jobsearch.details.domain.usecase

import com.example.jobsearch.details.domain.model.VacancyModel
import com.example.jobsearch.details.domain.repository.FavouriteRepository
import javax.inject.Inject

class DeleteFavouriteVacancyUseCaseImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
): DeleteFavouriteVacancyUseCase {
    override suspend fun delete(vacancyModel: VacancyModel): Int {
        return favouriteRepository.deleteFavourite(vacancyModel)
    }
}
