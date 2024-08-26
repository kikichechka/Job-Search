package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.model.Vacancy

interface DeleteFavouriteUseCase {
    fun deleteData(vacancy: Vacancy)
}
