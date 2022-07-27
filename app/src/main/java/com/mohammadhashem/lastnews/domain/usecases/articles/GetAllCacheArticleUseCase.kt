package com.mohammadhashem.lastnews.domain.usecases.articles

import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository

class GetAllCacheArticleUseCase (private val repository: ArticlesRepository) {
    operator fun invoke(source:SourceX) = repository.getCacheArticles(source)
}