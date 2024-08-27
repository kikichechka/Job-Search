package com.example.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.database.model.AddressDb
import com.example.database.model.ExperienceDb
import com.example.database.model.SalaryDb
import com.example.database.model.VacancyDatabase
import com.example.database.model.VacancyInfoDb

@Dao
interface VacancyDao {

    @Transaction
    @Query("SELECT * FROM vacancy")
    suspend fun getAll(): List<VacancyDatabase>

    @Query("DELETE FROM vacancy WHERE id = :id")
    suspend fun deleteVacancyInfoDb(id: String)

    @Query("DELETE FROM address WHERE vacancy_id = :id")
    suspend fun deleteAddressDb(id: String)

    @Query("DELETE FROM experience WHERE vacancy_id = :id")
    suspend fun deleteExperienceDb(id: String)

    @Query("DELETE FROM salary WHERE vacancy_id = :id")
    suspend fun deleteSalaryDb(id: String)

    suspend fun delete(vacancyDatabase: VacancyDatabase) {
        deleteVacancyInfoDb(vacancyDatabase.vacancyInfoDb.id)
        deleteAddressDb(vacancyDatabase.vacancyInfoDb.id)
        deleteExperienceDb(vacancyDatabase.vacancyInfoDb.id)
        deleteSalaryDb(vacancyDatabase.vacancyInfoDb.id)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancyInfoDb(vacancyInfoDb: VacancyInfoDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddressDb(addressDb: AddressDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExperienceDb(experienceDb: ExperienceDb)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSalaryDb(salaryDb: SalaryDb)

    suspend fun insert(vacancyDatabase: VacancyDatabase) {
        insertVacancyInfoDb(vacancyDatabase.vacancyInfoDb)
        insertAddressDb(vacancyDatabase.address)
        insertExperienceDb(vacancyDatabase.experience)
        insertSalaryDb(vacancyDatabase.salary)
    }
}
