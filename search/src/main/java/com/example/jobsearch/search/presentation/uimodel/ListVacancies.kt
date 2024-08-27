package com.example.jobsearch.search.presentation.uimodel

import android.os.Parcelable
import com.example.model.Vacancy
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListVacancies(
    val listVacancies: List<Vacancy>
): Parcelable
