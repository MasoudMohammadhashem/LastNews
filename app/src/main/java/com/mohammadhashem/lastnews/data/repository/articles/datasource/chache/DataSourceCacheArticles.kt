package com.mohammadhashem.lastnews.data.repository.articles.datasource.chache

import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.data.repository.articles.datasource.DataSourceArticles
import io.reactivex.rxjava3.core.Single


interface DataSourceCacheArticles:DataSourceArticles {
    override fun getRemoteArticles(
        sourceId: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Single<ArticlesResponse> {
        TODO("Not yet implemented")
    }

    override fun getCacheArticles(source: SourceX): Single<List<Article>>

    override fun addRemoteToCache(article: Article): Single<Long>

    override fun deleteCacheArticle(sourceX: SourceX)
}