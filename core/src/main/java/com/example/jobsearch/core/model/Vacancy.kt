package com.example.jobsearch.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Vacancy (
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
): Parcelable
