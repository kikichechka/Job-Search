package com.example.jobsearch.search.presentation.fragment.search

import androidx.lifecycle.ViewModel
import com.example.jobsearch.search.domain.model.AddressModel
import com.example.jobsearch.search.domain.model.ExperienceModel
import com.example.jobsearch.search.domain.model.SalaryModel
import com.example.jobsearch.search.domain.model.VacancyModel
import com.example.jobsearch.search.domain.usecase.AddFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.DeleteFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetCountFavouriteUseCase
import com.example.jobsearch.search.domain.usecase.GetDataUseCase
import com.example.jobsearch.search.presentation.uimodel.Offer
import com.example.jobsearch.search.presentation.uimodel.mapToUi
import com.example.model.Address
import com.example.model.Experience
import com.example.model.Salary
import com.example.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getCountFavouriteUseCase: GetCountFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase
) : ViewModel() {
    private val _offers = MutableStateFlow(listOf<Offer>())
    val offers = _offers.asStateFlow()

    private val _vacancyModel = MutableStateFlow(listOf<Vacancy>())
    val vacancy = _vacancyModel.asStateFlow()

    private val _favoriteVacancy = MutableStateFlow(0)
    val favoriteVacancy = _favoriteVacancy.asStateFlow()

    suspend fun loadData() {
        getCountFavourite()
        loadVacancies()
    }

    private suspend fun loadVacancies() {
        getDataUseCase.get()?.mapToUi()?.let {
            _offers.value = it.offers
            _vacancyModel.value = it.vacancies
        }
    }

    private suspend fun getCountFavourite() {
        getCountFavouriteUseCase.get().let {
            _favoriteVacancy.value = it
        }
    }

    suspend fun removeFromFavoritesVacancy(vacancy: Vacancy) {
        deleteFavouriteUseCase.delete(vacancy.mapToDomain())
        _vacancyModel.value.map { if (it.id == vacancy.id) it.isFavorite = false }
        getCountFavourite()
    }

    suspend fun addInFavoritesVacancy(vacancy: Vacancy) {
        vacancy.isFavorite = true
        addFavouriteUseCase.add(vacancy.mapToDomain())
        _vacancyModel.value.map { if (it.id == vacancy.id) it.isFavorite = true }
        getCountFavourite()
    }
}

fun AddressModel.mapToUi(): Address {
    return Address(
        town = town,
        street = street,
        house = house
    )
}

fun ExperienceModel.mapToUi(): Experience {
    return Experience(
        previewText = previewText,
        text = text
    )
}

fun SalaryModel.mapToUi(): Salary {
    return Salary(
        short = short,
        full = full
    )
}

fun VacancyModel.mapToUi(): Vacancy {
    return Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = addressModel.mapToUi(),
        company = company,
        experience = experienceModel.mapToUi(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salaryModel.mapToUi(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions,
    )
}

fun Vacancy.mapToDomain(): VacancyModel {
    return VacancyModel(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        addressModel = AddressModel(
            town = address.town,
            street = address.street,
            house = address.street
        ),
        company = company,
        experienceModel = ExperienceModel(
            previewText = experience.previewText,
            text = experience.text
        ),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salaryModel = SalaryModel(
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
