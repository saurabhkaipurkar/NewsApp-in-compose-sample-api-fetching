package com.saurabh.newsapp.apiservice

import com.saurabh.newsapp.apiservice.models.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getNews(
        apiKey: String,
        language: String = "hi,mr,en",
        country: String = "in",
        category: String = "breaking",
        page: String? = null
    ): NewsResponse {

        return client.get("api/1/latest") {
            parameter("apikey", apiKey)
            parameter("language", language)
            parameter("country", country)
            parameter("category", category)
            page?.let { parameter("page", it) }
        }.body()
    }
}