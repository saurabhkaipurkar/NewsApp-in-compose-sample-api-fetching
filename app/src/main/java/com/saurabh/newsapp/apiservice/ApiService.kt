package com.saurabh.newsapp.apiservice

import com.saurabh.newsapp.apiservice.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/1/latest")
    suspend fun getNews(
        @Query("apikey") apiKey: String,
        @Query("language") language: String = "hi,mr,en",
        @Query("country") country: String = "in",
        @Query("category") category: String = "breaking",
        @Query("page") page: String? = null
    ): NewsResponse
}