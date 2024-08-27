package com.example.jobsearch.details.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsearch.details.presenter.viewmodel.DetailsViewModel
import javax.inject.Inject

class DetailsViewModelsFactory @Inject constructor(
    private val detailsViewModel: DetailsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return detailsViewModel as T
        }

        throw IllegalAccessException("Unknown class name")
    }
}
