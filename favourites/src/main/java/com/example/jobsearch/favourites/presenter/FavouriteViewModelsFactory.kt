package com.example.jobsearch.favourites.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FavouriteViewModelsFactory @Inject constructor(
    private val favouriteViewModel: FavouriteViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return favouriteViewModel as T
        }

        throw IllegalAccessException("Unknown class name")
    }
}