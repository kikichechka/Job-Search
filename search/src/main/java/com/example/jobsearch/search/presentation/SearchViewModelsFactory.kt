package com.example.jobsearch.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SearchViewModelsFactory @Inject constructor(
    private val searchViewModel: SearchViewModel,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return searchViewModel as T
        }

        throw IllegalAccessException("Unknown class name")
    }
}