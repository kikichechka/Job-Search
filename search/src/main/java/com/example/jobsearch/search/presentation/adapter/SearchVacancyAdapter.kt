package com.example.jobsearch.search.presentation.adapter

import android.view.View
import com.example.jobsearch.search.presentation.uimodel.Vacancy

class SearchVacancyAdapter(
    private val countVacancies: String,
    private val clickVacancy: (vacancy: Vacancy) -> Unit,
    private val clickButtonAllVacancies: () -> Unit,
    clickFavourite: (vacancy: Vacancy) -> Unit,
    clickNotFavourite: (vacancy: Vacancy) -> Unit
) : VacancyAdapter(clickFavourite = clickFavourite, clickNotFavourite = clickNotFavourite) {

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        buttonAllVacancies(holder, position)
    }

    private fun buttonAllVacancies(holder: VacancyViewHolder, position: Int) {
        with(holder.binding.buttonAllVacancies) {
            visibility = View.GONE
            if (position == list.size - 1) {
                visibility = View.VISIBLE
                text = countVacancies
            }
            setOnClickListener {
                clickButtonAllVacancies()
            }
        }
    }
}
