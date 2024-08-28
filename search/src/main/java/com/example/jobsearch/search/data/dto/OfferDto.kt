package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.OfferModel

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
