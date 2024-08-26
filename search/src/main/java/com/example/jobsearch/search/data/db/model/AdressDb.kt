package com.example.jobsearch.search.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressDb(
    @PrimaryKey
    @ColumnInfo(name = "vacancy_id")
    val vacancyId: String,
    @ColumnInfo(name = "town")
    val town: String,
    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "house")
    val house: String
)
