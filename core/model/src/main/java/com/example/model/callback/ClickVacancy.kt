package com.example.model.callback

import com.example.model.Vacancy

interface ClickVacancy {
    fun openDetails(vacancy: Vacancy)
}