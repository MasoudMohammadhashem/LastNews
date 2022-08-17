package com.mohammadhashem.lastnews.domain.usecases.articles

import com.mohammadhashem.lastnews.domain.repository.ArticlePagingRepository

//import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository

class GetAllRemoteArticleUseCase (private val repository: ArticlePagingRepository){
    operator fun invoke(sourceId:String) = repository.getArticles(sourceId)
}