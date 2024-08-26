package com.example.jobsearch.favourites.domain.usecase

import com.example.jobsearch.favourites.domain.model.Vacancy

interface GetFavouriteUseCase {
    fun getData(): List<Vacancy>
}
