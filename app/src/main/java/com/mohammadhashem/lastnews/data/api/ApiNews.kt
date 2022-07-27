package com.mohammadhashem.lastnews.data.api

import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {
    //https://newsapi.org/v2/top-headlines/sources?apiKey=5cad169ddca3463ea68641e872efd90d خبرگزاری ها
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey: String): Single<SourcesResponse>

    //https://newsapi.org/v2/everything?sources=abc-news&page=1&pageSize=20&apiKey=5cad169ddca3463ea68641e872efd90d انتخاب من 20 پیج در هر ریکویست
    @GET("top-headlines")
    fun getHeadlines(
        @Query("sources") sourcesId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Single<ArticlesResponse>

}