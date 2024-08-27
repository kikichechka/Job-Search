package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Salary(
    val short: String?,
    val full: String
): Parcelable
