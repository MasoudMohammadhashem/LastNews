package com.mohammadhashem.lastnews.data.repository.articles.datasource.remote

import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.data.repository.articles.datasource.DataSourceArticles
import io.reactivex.rxjava3.core.Single

interface DataSourceRemoteArticles : DataSourceArticles {
    override fun getRemoteArticles(
        sourceId: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Single<ArticlesResponse>

    override fun getCacheArticles(source: SourceX): Single<List<Article>> {
        TODO("Not yet implemented")
    }

    override fun addRemoteToCache(article: Article): Single<Long> {
        TODO("Not yet implemented")
    }

    override fun deleteCacheArticle(sourceX: SourceX) {
        TODO("Not yet implemented")
    }
}