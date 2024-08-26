package com.example.jobsearch.favourites.domain.repository

import com.example.jobsearch.favourites.domain.model.Vacancy

interface GetFavouriteRepository {
    fun get(): List<Vacancy>
    fun delete(vacancy: Vacancy)
}
