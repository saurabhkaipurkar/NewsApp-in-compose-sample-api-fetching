package com.saurabh.newsapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saurabh.newsapp.apiservice.ApiService
import com.saurabh.newsapp.apiservice.models.NewsArticle

class NewsPagingSource(private val apiService: ApiService,private val apiKey: String) : PagingSource<String, NewsArticle>() {

    override fun getRefreshKey(state: PagingState<String, NewsArticle>): String? {
        // Cursor-based pagination → always start fresh
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, NewsArticle> {

        return try {
            val response = apiService.getNews(
                apiKey = apiKey,
                page = params.key,              // 🔑 cursor
                country = "in",
                language = "hi,mr,en",
                category = "breaking"
            )

            LoadResult.Page(
                data = response.results,
                prevKey = null,                 // ❌ no backward paging
                nextKey = response.nextPage     // ✅ cursor from API
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

}