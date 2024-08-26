package com.example.jobsearch.search.domain

import com.example.jobsearch.search.domain.model.Vacancy

interface AddFavouriteUseCase {
    suspend fun add(vacancy: Vacancy)
}
