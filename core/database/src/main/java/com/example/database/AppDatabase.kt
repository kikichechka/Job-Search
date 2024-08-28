package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.converters.ConvertersBoolean
import com.example.database.converters.ConvertersListString
import com.example.database.model.AddressDb
import com.example.database.model.ExperienceDb
import com.example.database.model.SalaryDb
import com.example.database.model.VacancyInfoDb

@Database(
    entities = [
        VacancyInfoDb::class,
        AddressDb::class,
        ExperienceDb::class,
        SalaryDb::class
    ],
    version = 9
)
@TypeConverters(
    ConvertersListString::class,
    ConvertersBoolean::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}
