package com.mohammadhashem.lastnews.domain.usecases.articles

data class AllUseCaseArticles(
    val addArticleToCacheUseCase: AddArticleToCacheUseCase,
    val deleteArticlesCacheUseCase: DeleteArticlesCacheUseCase,
    val getAllCacheArticleUseCase: GetAllCacheArticleUseCase,
    val getAllRemoteArticleUseCase: GetAllRemoteArticleUseCase
)
