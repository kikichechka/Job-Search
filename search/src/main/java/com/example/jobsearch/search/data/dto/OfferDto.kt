package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.Offer

data class OfferDto(
    val id: String?,
    val title: String,
    val button: TextButtonDto?,
    val link: String
)

fun OfferDto.mapToModel(): Offer {
    return Offer(
        id = id,
        title = title,
        button = button?.mapToModel(),
        link = link
    )
}
