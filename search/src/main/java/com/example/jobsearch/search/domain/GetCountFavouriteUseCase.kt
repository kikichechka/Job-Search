package com.example.jobsearch.search.domain

interface GetCountFavouriteUseCase {
    suspend fun get() : Int
}
