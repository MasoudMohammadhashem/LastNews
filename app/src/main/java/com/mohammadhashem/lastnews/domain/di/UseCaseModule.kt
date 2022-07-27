package com.mohammadhashem.lastnews.domain.di

import com.mohammadhashem.lastnews.data.repository.sources.SourcesRepository
import com.mohammadhashem.lastnews.domain.usecases.sources.GetAllSourceUseCases
import com.mohammadhashem.lastnews.domain.usecases.sources.GetSourceRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideProductUseCase(repository: SourcesRepository) =
        GetAllSourceUseCases(
            GetSourceRemoteUseCase(repository)
        )


}