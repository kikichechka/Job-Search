package com.example.jobsearch.details.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jobsearch.details.domain.model.AddressModel
import com.example.jobsearch.details.domain.model.ExperienceModel
import com.example.jobsearch.details.domain.model.SalaryModel
import com.example.jobsearch.details.domain.model.VacancyModel
import com.example.jobsearch.details.domain.usecase.AddFavouriteVacancyUseCase
import com.example.jobsearch.details.domain.usecase.DeleteFavouriteVacancyUseCase
import com.example.model.Vacancy
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val addFavouriteVacancyUseCase: AddFavouriteVacancyUseCase,
    private val deleteFavouriteVacancyUseCase: DeleteFavouriteVacancyUseCase
): ViewModel() {

    suspend fun addVacancy(vacancy: Vacancy) : Int {
        return addFavouriteVacancyUseCase.add(vacancy.mapToDomain())
    }

    suspend fun deleteVacancy(vacancy: Vacancy) : Int {
        return deleteFavouriteVacancyUseCase.delete(vacancy.mapToDomain())
    }
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
