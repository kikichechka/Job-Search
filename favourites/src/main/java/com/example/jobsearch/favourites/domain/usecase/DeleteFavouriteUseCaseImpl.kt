package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.repository.GetFavouriteRepository
import com.example.jobsearch.favourites.domain.model.Vacancy
import javax.inject.Inject

class DeleteFavouriteUseCaseImpl@Inject constructor(
    private val getFavouriteRepository: GetFavouriteRepository
): DeleteFavouriteUseCase {
    override fun deleteData(vacancy: Vacancy) {
        getFavouriteRepository.delete(vacancy)
    }
}
