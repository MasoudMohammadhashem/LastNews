package com.mohammadhashem.lastnews.data.pagination

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.mohammadhashem.lastnews.common.constants.PageSize
import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetArticleRxPagingSource (
    private val api: ApiNews,
    private val apiKey: String,
    private val sourceId: String,
    private val pageSize: Int
) : RxPagingSource<Int, Article>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {
        val page = params.key ?: 1

        return api.getHeadlines(sourceId, page, pageSize, apiKey)
            .subscribeOn(Schedulers.io())
            .map { transform(it) }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Article>, position: Int): LoadResult<Int, Article> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.size) null else position + 1
        )
    }

    private fun transform(response: ArticlesResponse): List<Article> {
        return with(response) {
            response.articles
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.coerceAtLeast(1)   // 1 is the first page
    }
}