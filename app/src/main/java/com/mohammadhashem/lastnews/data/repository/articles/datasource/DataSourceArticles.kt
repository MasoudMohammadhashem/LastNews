package com.mohammadhashem.lastnews.data.repository.articles.datasource

import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import io.reactivex.rxjava3.core.Single

interface DataSourceArticles {
    fun getRemoteArticles(sourceId:String,page:Int,pageSize:Int,apiKey:String) : Single<ArticlesResponse>
    fun getCacheArticles(source: SourceX) : Single<List<Article>>
    fun addRemoteToCache(article:Article) : Single<Long>
    fun deleteCacheArticle(sourceX: SourceX)
}