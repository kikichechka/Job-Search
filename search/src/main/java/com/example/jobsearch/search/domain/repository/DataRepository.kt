package com.example.jobsearch.search.domain.repository

import com.example.jobsearch.search.domain.model.AllData
import com.example.jobsearch.search.domain.model.Vacancy

interface DataRepository {
    suspend fun getAllData() : AllData?

    suspend fun getCountFavouriteVacancies() : Int

    suspend fun deleteFavourite(vacancy: Vacancy)

    suspend fun addFavourite(vacancy: Vacancy)
}
