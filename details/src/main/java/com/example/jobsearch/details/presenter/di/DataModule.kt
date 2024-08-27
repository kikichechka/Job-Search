package com.example.jobsearch.details.presenter.di

import com.example.jobsearch.details.data.datasource.FavouriteVacanciesLocalDataSource
import com.example.jobsearch.details.data.repository.FavouriteRepositoryImpl
import com.example.jobsearch.details.domain.repository.FavouriteRepository
import com.example.jobsearch.details.domain.usecase.AddFavouriteVacancyUseCase
import com.example.jobsearch.details.domain.usecase.AddFavouriteVacancyUseCaseImpl
import com.example.jobsearch.details.domain.usecase.DeleteFavouriteVacancyUseCase
import com.example.jobsearch.details.domain.usecase.DeleteFavouriteVacancyUseCaseImpl
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
    fun provideFavouriteRepository(favouriteVacanciesLocalDataSource: FavouriteVacanciesLocalDataSource): FavouriteRepository {
        return FavouriteRepositoryImpl(favouriteVacanciesLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideAddFavouriteVacancyUseCase(favouriteRepository: FavouriteRepository): AddFavouriteVacancyUseCase {
        return AddFavouriteVacancyUseCaseImpl(favouriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavouriteVacancyUseCase(favouriteRepository: FavouriteRepository): DeleteFavouriteVacancyUseCase {
        return DeleteFavouriteVacancyUseCaseImpl(favouriteRepository)
    }
}
