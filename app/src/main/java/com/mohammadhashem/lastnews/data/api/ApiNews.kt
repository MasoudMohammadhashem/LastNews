package com.mohammadhashem.lastnews.data.api

import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {
    //https://newsapi.org/v2/top-headlines/sources?apiKey=0a6fefea97e64fe386231da4b6464d41 خبرگزاری ها
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey: String): Single<SourcesResponse>

    //https://newsapi.org/v2/everything?sources=abc-news&page=1&pageSize=20&apiKey=0a6fefea97e64fe386231da4b6464d41 انتخاب من 20 پیج در هر ریکویست
    @GET("v2/everything")
    fun getHeadlines(
        @Query("sources") sourcesId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Single<ArticlesResponse>

}