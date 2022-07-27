package com.mohammadhashem.lastnews.data.repository.articles

import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.data.repository.articles.datasource.chache.DataSourceCacheArticles
import com.mohammadhashem.lastnews.data.repository.articles.datasource.remote.DataSourceRemoteArticles
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(private val remote: DataSourceRemoteArticles,
                                                 private val cache: DataSourceCacheArticles): ArticlesRepository {
    override fun getRemoteArticles(
        sourceId: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Single<ArticlesResponse> =remote.getRemoteArticles(sourceId,page,pageSize,apiKey)

    override fun getCacheArticles(source: SourceX): Single<List<Article>> = cache.getCacheArticles(source)

    override fun addRemoteToCache(article: Article): Single<Long> = cache.addRemoteToCache(article)

    override fun deleteCacheArticle(sourceX: SourceX) = cache.deleteCacheArticle(sourceX)
}