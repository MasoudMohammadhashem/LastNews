package com.mohammadhashem.lastnews.domain.usecases.articles

import androidx.paging.PagingData
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.repository.articles.ArticlePagingRepository
import io.reactivex.rxjava3.core.Flowable

//import com.mohammadhashem.lastnews.data.repository.articles.ArticlesRepository

class GetAllRemoteArticleUseCase (private val repository: ArticlePagingRepository){
    operator fun invoke(sourceId:String) = repository.getArticles(sourceId)
}