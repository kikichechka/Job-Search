package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salary")
data class SalaryDb @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    @ColumnInfo(name = "short")
    val short: String? = null,
    @ColumnInfo(name = "full")
    val full: String
)
