package com.example.jobsearch.search.domain.repository

import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.jobsearch.search.domain.model.VacancyModel

interface DataRepository {
    suspend fun getAllData() : AllDataModel?

    suspend fun getCountFavouriteVacancies() : Int

    suspend fun deleteFavourite(vacancyModel: VacancyModel)

    suspend fun addFavourite(vacancyModel: VacancyModel)
}
