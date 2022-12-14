package com.mohammadhashem.lastnews.domain.usecases.articles

import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.domain.repository.ArticlePagingRepository

//import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository

class AddArticleToCacheUseCase (private val repository: ArticlePagingRepository) {
    operator fun invoke(article: Article) = repository.addRemoteToCache(article)
}