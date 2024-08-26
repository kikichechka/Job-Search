package com.example.jobsearch.search.presentation.adapter

import android.annotation.SuppressLint
import android.view.View
import com.example.jobsearch.core.R
import com.example.jobsearch.core.model.Vacancy
import com.example.jobsearch.core.adapter.VacancyAdapter

class SearchVacancyAdapter(
    private var countVacancies: String,
    private val clickButtonAllVacancies: () -> Unit,
    clickVacancy: (vacancy: Vacancy) -> Unit,
    clickFavourite: (vacancy: Vacancy) -> Unit,
    clickNotFavourite: (vacancy: Vacancy) -> Unit
) : VacancyAdapter(clickFavourite = clickFavourite, clickNotFavourite = clickNotFavourite, clickVacancy = clickVacancy) {

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        buttonAllVacancies(holder, position)
    }

    fun changeCountVacancies(countVacancies: String) {
        this.countVacancies = countVacancies
        notifyItemChanged(list.size-1)
    }

    @SuppressLint("SetTextI18n")
    private fun buttonAllVacancies(holder: VacancyViewHolder, position: Int) {
        with(holder.binding.buttonAllVacancies) {
            visibility = View.GONE
            if (position == list.size - 1) {
                visibility = View.VISIBLE
                text = "${resources.getString(R.string.still)} $countVacancies"
            }
            setOnClickListener {
                clickButtonAllVacancies()
            }
        }
    }
}
