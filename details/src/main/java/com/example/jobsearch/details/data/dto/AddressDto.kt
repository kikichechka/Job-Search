package com.example.jobsearch.details.data.dto

import com.example.jobsearch.details.domain.model.AddressModel

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

fun AddressModel.mapToDto(): AddressDto {
    return AddressDto(
        town = town,
        street = street,
        house = house
    )
}
