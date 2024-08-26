package com.example.jobsearch.search.domain.usecase

interface GetCountFavouriteUseCase {
    suspend fun get() : Int
}
