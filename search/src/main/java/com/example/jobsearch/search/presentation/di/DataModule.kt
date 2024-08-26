package com.example.jobsearch.search.presentation.di

import com.example.jobsearch.search.data.datasource.OffersAndVacanciesRemoteDataSource
import com.example.jobsearch.search.data.datasource.VacanciesLocalDataSource
import com.example.jobsearch.search.data.repository.DataRepositoryImpl
import com.example.jobsearch.search.domain.AddFavouriteUseCase
import com.example.jobsearch.search.domain.AddFavouriteUseCaseImpl
import com.example.jobsearch.search.domain.DataRepository
import com.example.jobsearch.search.domain.DeleteFavouriteUseCase
import com.example.jobsearch.search.domain.DeleteFavouriteUseCaseImpl
import com.example.jobsearch.search.domain.GetCountFavouriteUseCase
import com.example.jobsearch.search.domain.GetCountFavouriteUseCaseImpl
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
    fun provideDataRepository(offersAndVacanciesRemoteDataSource: OffersAndVacanciesRemoteDataSource,
                              vacanciesLocalDataSource: VacanciesLocalDataSource): DataRepository {
        return DataRepositoryImpl(offersAndVacanciesRemoteDataSource, vacanciesLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideDataUseCase(dataRepository: DataRepository): GetDataUseCase {
        return GetDataUseCaseImpl(dataRepository)
    }

    @Provides
    @Singleton
    fun provideCountFavouriteUseCase(dataRepository: DataRepository): GetCountFavouriteUseCase {
        return GetCountFavouriteUseCaseImpl(dataRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavouriteUseCase(dataRepository: DataRepository): DeleteFavouriteUseCase {
        return DeleteFavouriteUseCaseImpl(dataRepository)
    }

    @Provides
    @Singleton
    fun provideAddFavouriteUseCase(dataRepository: DataRepository): AddFavouriteUseCase {
        return AddFavouriteUseCaseImpl(dataRepository)
    }
}
