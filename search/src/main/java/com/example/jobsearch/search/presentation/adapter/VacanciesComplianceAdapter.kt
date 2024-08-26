package com.example.jobsearch.search.presentation.adapter

import com.example.jobsearch.core.adapter.VacancyAdapter
import com.example.jobsearch.core.model.Vacancy

class VacanciesComplianceAdapter(
    clickVacancy: (vacancy: Vacancy) -> Unit,
    clickFavourite: (vacancy: Vacancy) -> Unit,
    clickNotFavourite: (vacancy: Vacancy) -> Unit
) : VacancyAdapter(clickFavourite = clickFavourite, clickNotFavourite = clickNotFavourite, clickVacancy = clickVacancy)

