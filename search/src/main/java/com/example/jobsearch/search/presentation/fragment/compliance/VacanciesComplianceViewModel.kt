package com.example.jobsearch.search.presentation.fragment.compliance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.core.model.Vacancy
import com.example.jobsearch.search.domain.usecase.AddFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.DeleteFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetCountFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetDataUseCase
import com.example.jobsearch.search.presentation.fragment.search.mapToDomain
import com.example.jobsearch.search.presentation.uimodel.mapToUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VacanciesComplianceViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getCountFavouriteUseCase: GetCountFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase
) : ViewModel() {
    private val _vacancy = MutableStateFlow(listOf<Vacancy>())
    val vacancy = _vacancy.asStateFlow()

    private val _favoriteVacancy = MutableStateFlow(0)
    val favoriteVacancy = _favoriteVacancy.asStateFlow()

    init {
        viewModelScope.launch {
            _favoriteVacancy.value = getCountFavouriteUseCase.get()
            getDataUseCase.get()?.mapToUi()?.let {
                _vacancy.value = it.vacancies
            }
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
