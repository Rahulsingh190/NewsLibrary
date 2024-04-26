package com.news.newslibrary

import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiCaller(baseUrl: String) : ApiService {

    private val retrofit: Retrofit
    private var apiService: ApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override suspend fun getNewsHeadlines(
        apiKey: String,
        sources: String?
    ): NewsResponse {
        return apiService.getNewsHeadlines(apiKey, sources)
    }

    override suspend fun getNewsSources(apiKey: String): NewsSourceResponse {
        return apiService.getNewsSources(apiKey)
    }
}