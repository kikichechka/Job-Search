package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.Address

class Address(
    val town: String,
    val street: String,
    val house: String
)

fun Address.mapToUi(): com.example.jobsearch.search.presentation.uimodel.Address {
    return com.example.jobsearch.search.presentation.uimodel.Address(
        town = town,
        street = street,
        house = house
    )
}
