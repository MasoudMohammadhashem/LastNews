package com.mohammadhashem.lastnews.data.api

import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey: String): Single<SourcesResponse>

    @GET("v2/everything")
    fun getHeadlines(
        @Query("sources") sourcesId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Single<ArticlesResponse>

}