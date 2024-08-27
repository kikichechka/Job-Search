package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.AllDataModel
import com.example.jobsearch.search.domain.model.OfferModel
import com.example.network.dto.AllDataRemote
import com.example.network.dto.OfferRemote

data class OfferDto(
    val id: String?,
    val title: String,
    val button: TextButtonDto?,
    val link: String
)

fun OfferDto.mapToModel(): OfferModel {
    return OfferModel(
        id = id,
        title = title,
        button = button?.mapToModel(),
        link = link
    )
}

fun OfferRemote.mapToDto(): OfferDto {
    return OfferDto(
        id = id,
        title = title,
        button = button?.mapToDto(),
        link = link
    )
}
