package com.example.jobsearch.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearch.search.presentation.fragment.compliance.VacanciesComplianceViewModel
import com.example.jobsearch.search.presentation.fragment.search.SearchViewModel
import javax.inject.Inject

class SearchViewModelsFactory @Inject constructor(
    private val searchViewModel: SearchViewModel,
    private val vacanciesComplianceViewModel: VacanciesComplianceViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return searchViewModel as T
        }
        if (modelClass.isAssignableFrom(VacanciesComplianceViewModel::class.java)) {
            return vacanciesComplianceViewModel as T
        }

        throw IllegalAccessException("Unknown class name")
    }
}
