package com.example.jobsearch.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Salary(
    val short: String?,
    val full: String
): Parcelable
