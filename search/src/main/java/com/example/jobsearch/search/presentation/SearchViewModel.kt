package com.example.jobsearch.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.search.domain.GetDataUseCase
import com.example.jobsearch.search.presentation.uimodel.Offer
import com.example.jobsearch.search.presentation.uimodel.Vacancy
import com.example.jobsearch.search.presentation.uimodel.mapToUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
): ViewModel() {
    private val _offers = MutableStateFlow(listOf<Offer>())
    val offers = _offers.asStateFlow()

    private val _vacancy = MutableStateFlow(listOf<Vacancy>())
    val vacancy = _vacancy.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            getDataUseCase.get()?.mapToUi()?.let {
                _offers.value = it.offers
                _vacancy.value = it.vacancies
            }
        }
    }
}
