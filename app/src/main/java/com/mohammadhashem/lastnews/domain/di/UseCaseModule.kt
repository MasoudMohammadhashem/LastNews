package com.mohammadhashem.lastnews.domain.di

//import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository
import com.mohammadhashem.lastnews.data.repository.articles.ArticlePagingRepository
import com.mohammadhashem.lastnews.data.repository.sources.SourcesRepository
import com.mohammadhashem.lastnews.domain.usecases.articles.*
import com.mohammadhashem.lastnews.domain.usecases.sources.AllUseCasesSource
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
    fun provideSourceUseCase(repository: SourcesRepository) =
        AllUseCasesSource(
            GetSourceRemoteUseCase(repository)
        )

//    @Singleton
//    @Provides
//    fun provideArticleUseCase(repository: ArticlesRepository) =
//        AllUseCaseArticles(
//            AddArticleToCacheUseCase(repository),
//            DeleteArticlesCacheUseCase(repository),
//            GetAllCacheArticleUseCase(repository),
//            GetAllRemoteArticleUseCase(repository)
//
//        )
@Singleton
@Provides
fun provideArticleUseCase(repository: ArticlePagingRepository) =
    AllUseCaseArticles(
        AddArticleToCacheUseCase(repository),
        DeleteArticlesCacheUseCase(repository),
        GetAllCacheArticleUseCase(repository),
        GetAllRemoteArticleUseCase(repository)

    )
}