package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.repository.GetFavouriteRepository
import com.example.jobsearch.favourites.domain.model.Vacancy
import javax.inject.Inject

class GetFavouriteUseCaseImpl @Inject constructor(
    private val getFavouriteRepository: GetFavouriteRepository
): GetFavouriteUseCase {
    override fun getData(): List<Vacancy> {
        return getFavouriteRepository.get()
    }
}
