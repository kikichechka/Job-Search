package com.example.jobsearch.favourites.domain.model

import com.example.jobsearch.favourites.domain.model.Address
import com.example.jobsearch.favourites.domain.model.Experience
import com.example.jobsearch.favourites.domain.model.Salary

class Vacancy (
        val id: String,
        val lookingNumber: Int?,
        val title: String,
        val address: Address,
        val company: String,
        val experience: Experience,
        val publishedDate: String,
        var isFavorite: Boolean,
        val salary: Salary,
        val schedules: List<String>,
        val appliedNumber: Int?,
        val description: String?,
        val responsibilities: String,
        val questions: List<String>,
)