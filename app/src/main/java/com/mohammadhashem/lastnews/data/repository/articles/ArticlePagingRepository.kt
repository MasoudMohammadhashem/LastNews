package com.mohammadhashem.lastnews.data.repository.articles

import androidx.paging.PagingData
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ArticlePagingRepository {
    fun getArticles(sourceId:String): Flowable<PagingData<Article>>
    fun getCacheArticles(source: SourceX) : Single<List<Article>>
    fun addRemoteToCache(article: Article) : Single<Long>
    fun deleteCacheArticle(sourceX: SourceX)
}