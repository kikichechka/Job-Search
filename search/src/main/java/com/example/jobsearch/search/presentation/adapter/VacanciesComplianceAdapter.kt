package com.example.jobsearch.search.presentation.adapter

import com.example.jobsearch.search.presentation.uimodel.Vacancy

class VacanciesComplianceAdapter(
    private val clickVacancy: (vacancy: Vacancy) -> Unit,
    clickFavourite: (position: Int) -> Unit,
    clickNotFavourite: (position: Int) -> Unit
) : VacancyAdapter(clickFavourite = clickFavourite, clickNotFavourite = clickNotFavourite)

