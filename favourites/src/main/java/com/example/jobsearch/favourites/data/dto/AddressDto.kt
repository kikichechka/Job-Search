package com.example.jobsearch.favourites.data.dto

import com.example.jobsearch.favourites.domain.model.Address

class AddressDto(
    val town: String,
    val street: String,
    val house: String
)

fun AddressDto.mapToModel(): Address {
    return Address(
        town = town,
        street = street,
        house = house
    )
}
