package com.example.jobsearch.favourites.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearch.favourites.data.dto.AddressDto
import com.example.jobsearch.favourites.data.dto.ExperienceDto
import com.example.jobsearch.favourites.data.dto.SalaryDto
import com.example.jobsearch.favourites.data.dto.VacancyDto
import com.example.jobsearch.favourites.domain.model.AddressModel
import com.example.jobsearch.favourites.domain.model.ExperienceModel
import com.example.jobsearch.favourites.domain.model.SalaryModel
import com.example.jobsearch.favourites.domain.model.VacancyModel
import com.example.jobsearch.favourites.domain.usecase.DeleteFavouriteUseCase
import com.example.jobsearch.favourites.domain.usecase.GetFavouriteUseCase
import com.example.model.Address
import com.example.model.Experience
import com.example.model.Salary
import com.example.model.Vacancy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase
) : ViewModel() {
    private val _vacancy = MutableStateFlow(listOf<Vacancy>())
    val vacancy = _vacancy.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _vacancy.value = getFavouriteUseCase.getData().map { it.mapToUi() }
        }
    }

    suspend fun deleteFavouriteVacancy(vacancy: Vacancy) {
        deleteFavouriteUseCase.deleteData(vacancy.mapToDomain())
        loadData()
    }
}

fun AddressModel.mapToUi(): Address {
    return Address(
        town = town,
        street = street,
        house = house
    )
}

fun AddressModel.mapToDto(): AddressDto {
    return AddressDto(
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

fun ExperienceModel.mapToDto(): ExperienceDto {
    return ExperienceDto(
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

fun SalaryModel.mapToDto(): SalaryDto {
    return SalaryDto(
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
        experience = experience.mapToUi(),
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

fun VacancyModel.mapToDto(): VacancyDto {
    return VacancyDto(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = addressModel.mapToDto(),
        company = company,
        experience = experience.mapToDto(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salaryModel.mapToDto(),
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
        experience = ExperienceModel(
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
