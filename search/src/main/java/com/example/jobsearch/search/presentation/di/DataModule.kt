package com.example.jobsearch.search.presentation.di

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.repository.DataRepositoryImpl
import com.example.jobsearch.search.domain.DataRepository
import com.example.jobsearch.search.domain.GetDataUseCase
import com.example.jobsearch.search.domain.GetDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDataRepository(offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource): DataRepository {
        return DataRepositoryImpl(offersAndVacanciesRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDataUseCase(dataRepository: DataRepository): GetDataUseCase {
        return GetDataUseCaseImpl(dataRepository)
    }
}
