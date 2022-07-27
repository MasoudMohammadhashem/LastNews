package com.mohammadhashem.lastnews.data.di

import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository
import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepositoryImpl
import com.mohammadhashem.lastnews.data.repository.sources.SourcesRepository
import com.mohammadhashem.lastnews.data.repository.sources.SourcesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModue {

    @Singleton
    @Binds
    fun bindResourceRepository(repository: SourcesRepositoryImpl): SourcesRepository

    @Singleton
    @Binds
    fun bindArticleRepository(repository: ArticlesRepositoryImpl): ArticlesRepository
}