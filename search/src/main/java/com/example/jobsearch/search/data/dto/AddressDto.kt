package com.example.jobsearch.search.data.dto

import com.example.database.model.AddressDb
import com.example.jobsearch.search.domain.model.AddressModel
import com.example.network.dto.AddressRemote

data class AddressDto(
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

fun AddressRemote.mapToDto(): AddressDto {
    return AddressDto(
        town = town,
        street = street,
        house = house
    )
}
