package com.example.jobsearch.search.presentation.uimodel

import com.example.jobsearch.search.domain.model.OfferModel

class Offer(
    val id: String?,
    val title: String,
    val button: TextButton?,
    val link: String
)

fun OfferModel.mapToUi(): Offer {
    return Offer(
        id = id,
        title = title,
        button = button?.mapToUi(),
        link = link
    )
}
