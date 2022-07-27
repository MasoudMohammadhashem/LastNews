package com.mohammadhashem.lastnews.data.di

import com.google.gson.Gson
import com.mohammadhashem.lastnews.common.constants.BASE_URL
import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.repository.articles.datasource.DataSourceArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.remote.DataSourceRemoteArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.remote.DataSourceRemoteArticlesImpl
import com.mohammadhashem.lastnews.data.repository.sources.datasource.DataSourceSources
import com.mohammadhashem.lastnews.data.repository.sources.datasource.remote.DataSourceSourcesRemote
import com.mohammadhashem.lastnews.data.repository.sources.datasource.remote.DataSourceSourcesRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }


    @Singleton
    @Provides
    fun provideNewsApi(): ApiNews {

        return Retrofit.Builder()
            .baseUrl(BASE_URL) //                .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiNews::class.java)
    }

    @Singleton
    @Provides
    fun provideDataSourceRemoteSources(api: ApiNews): DataSourceSources {
        return DataSourceSourcesRemoteImpl(api)
    }

    @Singleton
    @Provides
    fun provideDataSourceRemoteArticles(api: ApiNews): DataSourceRemoteArticles {
        return DataSourceRemoteArticlesImpl(api)
    }
}