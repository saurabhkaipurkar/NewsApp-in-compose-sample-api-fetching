package com.saurabh.newsapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.saurabh.newsapp.apiservice.ApiService
import com.saurabh.newsapp.paging.NewsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val apiService: ApiService) {

    companion object {
        private const val APIKEY = "pub_e560f67af15e4f32b5519cc70b648f52"
    }

    fun getBreakingNews() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    apiService = apiService,
                    apiKey = APIKEY
                )
            }
        ).flow
}