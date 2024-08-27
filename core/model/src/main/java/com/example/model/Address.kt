package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    val town: String,
    val street: String,
    val house: String
): Parcelable
