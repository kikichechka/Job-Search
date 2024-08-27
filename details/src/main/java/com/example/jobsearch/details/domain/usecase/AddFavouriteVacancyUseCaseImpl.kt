package com.example.jobsearch.details.domain.usecase

import com.example.jobsearch.details.domain.model.VacancyModel
import com.example.jobsearch.details.domain.repository.FavouriteRepository
import javax.inject.Inject

class AddFavouriteVacancyUseCaseImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) : AddFavouriteVacancyUseCase{
    override suspend fun add(vacancyModel: VacancyModel): Int {
        return favouriteRepository.addFavourite(vacancyModel)
    }
}
