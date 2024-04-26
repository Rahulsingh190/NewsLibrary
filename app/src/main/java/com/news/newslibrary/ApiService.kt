package com.news.newslibrary

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(@Query("apiKey") apiKey: String, @Query("sources") sources: String? = null): NewsResponse

    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(@Query("apiKey") apiKey: String): NewsSourceResponse
}

