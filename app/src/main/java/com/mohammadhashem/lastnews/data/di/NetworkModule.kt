package com.mohammadhashem.lastnews.data.di

import android.content.Context
import com.google.gson.Gson
import com.mohammadhashem.lastnews.common.constants.BASE_URL
import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.repository.articles.datasource.remote.DataSourceRemoteArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.remote.DataSourceRemoteArticlesImpl
import com.mohammadhashem.lastnews.data.repository.sources.datasource.DataSourceSources
import com.mohammadhashem.lastnews.data.repository.sources.datasource.remote.DataSourceSourcesRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
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
    fun provideNewsApi(@ApplicationContext appContext: Context): ApiNews {
        val onlineInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60 * 15 // read from cache for 15 min even if there is internet connection
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
        val cacheSize = (50 * 1024 * 1024).toLong() // 50 MB for cache
        val cache = Cache(appContext.cacheDir, cacheSize)
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder()
                .addNetworkInterceptor(onlineInterceptor)
                .cache(cache)
                .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL) //
            .client(okHttpClient)
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