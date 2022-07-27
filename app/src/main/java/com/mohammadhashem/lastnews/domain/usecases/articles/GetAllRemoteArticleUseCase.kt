package com.mohammadhashem.lastnews.domain.usecases.articles

import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository

class GetAllRemoteArticleUseCase (private val repository: ArticlesRepository) {
    operator fun invoke(sourceId:String,page:Int,pageSize:Int,apiKey : String) = repository.getRemoteArticles(sourceId,page,pageSize,apiKey)
}