package com.example.jobsearch.search.presentation.adapter

import com.example.jobsearch.search.presentation.uimodel.Vacancy

class VacanciesComplianceAdapter(
    private val clickVacancy: (vacancy: Vacancy) -> Unit,
    clickFavourite: (vacancy: Vacancy) -> Unit,
    clickNotFavourite: (vacancy: Vacancy) -> Unit
) : VacancyAdapter(clickFavourite = clickFavourite, clickNotFavourite = clickNotFavourite)

