package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.Address

data class AddressDto(
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
