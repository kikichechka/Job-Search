package com.example.jobsearch.favourites.presenter.di

import com.example.jobsearch.favourites.data.datasource.FavouriteVacanciesLocalDataSource
import com.example.jobsearch.favourites.data.repository.FavouriteRepositoryImpl
import com.example.jobsearch.favourites.domain.repository.FavouriteRepository
import com.example.jobsearch.favourites.domain.usecase.DeleteFavouriteUseCase
import com.example.jobsearch.favourites.domain.usecase.DeleteFavouriteUseCaseImpl
import com.example.jobsearch.favourites.domain.usecase.GetFavouriteUseCase
import com.example.jobsearch.favourites.domain.usecase.GetFavouriteUseCaseImpl
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
    fun provideGetFavouriteUseCase(favouriteRepository: FavouriteRepository): GetFavouriteUseCase {
        return GetFavouriteUseCaseImpl(favouriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavouriteUseCase(favouriteRepository: FavouriteRepository): DeleteFavouriteUseCase {
        return DeleteFavouriteUseCaseImpl(favouriteRepository)
    }
}
