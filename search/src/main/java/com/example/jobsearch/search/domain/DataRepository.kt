package com.example.jobsearch.search.domain

import com.example.jobsearch.search.domain.model.AllData

interface DataRepository {
    suspend fun getData() : AllData?
}
