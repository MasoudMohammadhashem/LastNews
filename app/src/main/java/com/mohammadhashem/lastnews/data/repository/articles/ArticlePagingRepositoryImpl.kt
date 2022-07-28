package com.mohammadhashem.lastnews.data.repository.articles

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.mohammadhashem.lastnews.common.constants.API_KEY
import com.mohammadhashem.lastnews.common.constants.PageSize
import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.data.pagination.GetArticleRxPagingSource
import com.mohammadhashem.lastnews.data.repository.articles.datasource.chache.DataSourceCacheArticles
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ArticlePagingRepositoryImpl@Inject constructor(private val api: ApiNews,
                                                     private val cache: DataSourceCacheArticles): ArticlePagingRepository {
    override fun getArticles(sourceId:String): Flowable<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = PageSize,
                enablePlaceholders = true,
//                maxSize = 30,
//                prefetchDistance = 5,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { GetArticleRxPagingSource(api, API_KEY,sourceId, PageSize) }
        ).flowable
    }


    override fun getCacheArticles(source: SourceX): Single<List<Article>> = cache.getCacheArticles(source)

    override fun addRemoteToCache(article: Article): Single<Long> = cache.addRemoteToCache(article)

    override fun deleteCacheArticle(sourceX: SourceX) = cache.deleteCacheArticle(sourceX)
}