package com.example.jobsearch.search.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

fun Address.mapToDto(): AddressDto {
    return AddressDto(
        town = town,
        street = street,
        house = house
    )
}
