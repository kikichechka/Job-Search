package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.repository.FavouriteRepository
import com.example.jobsearch.favourites.domain.model.VacancyModel
import javax.inject.Inject

class DeleteFavouriteUseCaseImpl@Inject constructor(
    private val favouriteRepository: FavouriteRepository
): DeleteFavouriteUseCase {
    override suspend fun deleteData(vacancyModel: VacancyModel) {
        favouriteRepository.delete(vacancyModel)
    }
}
