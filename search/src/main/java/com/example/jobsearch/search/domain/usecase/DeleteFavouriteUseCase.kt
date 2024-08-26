package com.example.jobsearch.search.domain.usecase

import com.example.jobsearch.search.domain.model.Vacancy

interface DeleteFavouriteUseCase {
    suspend fun delete(vacancy: Vacancy)
}
