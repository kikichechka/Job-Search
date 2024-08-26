package com.example.jobsearch.search.presentation.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.core.model.Vacancy
import com.example.jobsearch.search.domain.model.Address
import com.example.jobsearch.search.domain.model.Experience
import com.example.jobsearch.search.domain.model.Salary
import com.example.jobsearch.search.domain.usecase.AddFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.DeleteFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetCountFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetDataUseCase
import com.example.jobsearch.search.presentation.uimodel.Offer
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

    init {
        viewModelScope.launch {
            getCountFavourite()
        }
        viewModelScope.launch {
            getDataUseCase.get()?.mapToUi()?.let {
                _offers.value = it.offers
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

fun Address.mapToUi(): com.example.jobsearch.core.model.Address {
    return com.example.jobsearch.core.model.Address(
        town = town,
        street = street,
        house = house
    )
}

fun Experience.mapToUi(): com.example.jobsearch.core.model.Experience {
    return com.example.jobsearch.core.model.Experience(
        previewText = previewText,
        text = text
    )
}

fun Salary.mapToUi(): com.example.jobsearch.core.model.Salary {
    return com.example.jobsearch.core.model.Salary(
        short = short,
        full = full
    )
}

fun com.example.jobsearch.search.domain.model.Vacancy.mapToUi(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = address.mapToUi(),
        company = company,
        experience = experience.mapToUi(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.mapToUi(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}

fun Vacancy.mapToDomain(): com.example.jobsearch.search.domain.model.Vacancy {
    return com.example.jobsearch.search.domain.model.Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = Address(
            town = address.town,
            street = address.street,
            house = address.street
        ),
        company = company,
        experience = Experience(
            previewText = experience.previewText,
            text = experience.text
        ),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = Salary(
            short = salary.short,
            full = salary.full
        ),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}
