package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.AddressModel

class AddressDto(
    val town: String,
    val street: String,
    val house: String
)

fun AddressDto.mapToModel(): AddressModel {
    return AddressModel(
        town = town,
        street = street,
        house = house
    )
}
