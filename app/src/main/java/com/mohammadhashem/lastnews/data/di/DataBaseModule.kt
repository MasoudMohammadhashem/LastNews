package com.mohammadhashem.lastnews.data.di

import android.content.Context
import androidx.room.Room
import com.mohammadhashem.lastnews.data.database.DataBaseArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.chache.DataSourceCacheArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.chache.DataSourceCacheArticlesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, DataBaseArticles::class.java, "ArticlesDatabase"
    ).fallbackToDestructiveMigration().build()
//        .allowMainThreadQueries()

    @Singleton
    @Provides
    fun provideDataSourceArticleCache(dataBaseArticles: DataBaseArticles):DataSourceCacheArticles =
        DataSourceCacheArticlesImpl(dataBaseArticles)
}