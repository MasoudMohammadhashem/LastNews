package com.mohammadhashem.lastnews.data.repository.articles.datasource.remote

import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceRemoteArticlesImpl @Inject constructor(private val apiNews: ApiNews) : DataSourceRemoteArticles {
    override fun getRemoteArticles(
        sourceId: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): Single<ArticlesResponse> = apiNews.getHeadlines(sourceId,page,pageSize,apiKey)
}