package com.example.jobsearch.search.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jobsearch.search.data.db.converters.ConvertersBoolean
import com.example.jobsearch.search.data.db.converters.ConvertersListString
import com.example.jobsearch.search.data.db.model.AddressDb
import com.example.jobsearch.search.data.db.model.ExperienceDb
import com.example.jobsearch.search.data.db.model.SalaryDb
import com.example.jobsearch.search.data.db.model.VacancyInfoDb

@Database(
    entities = [
        VacancyInfoDb::class,
        AddressDb::class,
        ExperienceDb::class,
        SalaryDb::class
    ],
    version = 5
)
@TypeConverters(
    ConvertersListString::class,
    ConvertersBoolean::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}
