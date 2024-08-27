package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.repository.FavouriteRepository
import com.example.jobsearch.favourites.domain.model.VacancyModel
import javax.inject.Inject

class GetFavouriteUseCaseImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
): GetFavouriteUseCase {
    override suspend fun getData(): List<VacancyModel> {
        return favouriteRepository.get()
    }
}
