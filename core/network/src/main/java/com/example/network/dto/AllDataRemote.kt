package com.example.network.dto

data class AllDataRemote(
    val offers: List<OfferRemote>,
    var vacancies: List<VacancyRemote>
)
