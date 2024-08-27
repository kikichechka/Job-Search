package com.example.jobsearch.favourites.presenter

import com.example.adapter.VacancyAdapter
import com.example.model.Vacancy

class FavouriteVacanciesAdapter (
    clickVacancy: (vacancy: Vacancy) -> Unit,
    clickFavourite: (vacancy: Vacancy) -> Unit,
    clickNotFavourite: (vacancy: Vacancy) -> Unit
) : VacancyAdapter(
    clickFavourite = clickFavourite,
    clickNotFavourite = clickNotFavourite,
    clickVacancy = clickVacancy
)
