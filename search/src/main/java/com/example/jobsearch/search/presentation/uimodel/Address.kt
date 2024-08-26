package com.example.jobsearch.search.presentation.uimodel

import android.os.Parcelable
import com.example.jobsearch.search.domain.model.Address
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    val town: String,
    val street: String,
    val house: String
): Parcelable

fun Address.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Address {
    return com.example.jobsearch.search.presentation.uimodel.Address(
        town = town,
        street = street,
        house = house
    )
}
