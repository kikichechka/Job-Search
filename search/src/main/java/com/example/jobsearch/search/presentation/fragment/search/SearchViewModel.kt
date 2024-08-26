package com.example.jobsearch.search.presentation.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.search.domain.AddFavouriteUseCase
import com.example.jobsearch.search.domain.DeleteFavouriteUseCase
import com.example.jobsearch.search.domain.GetCountFavouriteUseCase
import com.example.jobsearch.search.domain.GetDataUseCase
import com.example.jobsearch.search.presentation.uimodel.Offer
import com.example.jobsearch.search.presentation.uimodel.Vacancy
import com.example.jobsearch.search.presentation.uimodel.mapToDomain
import com.example.jobsearch.search.presentation.uimodel.mapToUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getCountFavouriteUseCase: GetCountFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase
): ViewModel() {
    private val _offers = MutableStateFlow(listOf<Offer>())
    val offers = _offers.asStateFlow()

    private val _vacancy = MutableStateFlow(listOf<Vacancy>())
    val vacancy = _vacancy.asStateFlow()

    private val _favoriteVacancy = MutableStateFlow(0)
    val favoriteVacancy = _favoriteVacancy.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            getDataUseCase.get()?.mapToUi()?.let {
                _offers.value = it.offers
                _vacancy.value = it.vacancies
            }
            getCountFavourite()
        }
    }

    private suspend fun getCountFavourite() {
        getCountFavouriteUseCase.get().let {
            _favoriteVacancy.value = it
        }
    }

    suspend fun removeFromFavoritesVacancy(vacancy: Vacancy) {
        deleteFavouriteUseCase.delete(vacancy.mapToDomain())
        _vacancy.value.map { if (it.id == vacancy.id) it.isFavorite = false }
        getCountFavourite()
    }

    suspend fun addInFavoritesVacancy(vacancy: Vacancy) {
        addFavouriteUseCase.add(vacancy.mapToDomain())
        _vacancy.value.map { if (it.id == vacancy.id) it.isFavorite = true }
        getCountFavourite()
    }
}
