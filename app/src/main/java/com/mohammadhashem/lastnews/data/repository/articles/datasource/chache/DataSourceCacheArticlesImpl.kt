package com.mohammadhashem.lastnews.data.repository.articles.datasource.chache

import com.mohammadhashem.lastnews.data.database.DataBaseArticles
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceCacheArticlesImpl @Inject constructor(private val dataBaseArticles: DataBaseArticles):DataSourceCacheArticles{

    private val daoClass = dataBaseArticles.articleDao()

    override fun getCacheArticles(source: SourceX): Single<List<Article>> = daoClass.getAllCacheArticles(source)

    override fun addRemoteToCache(article: Article): Single<Long> = daoClass.insertArticles(article)

    override fun deleteCacheArticle(sourceX: SourceX) = daoClass.deleteArticles(sourceX)
}