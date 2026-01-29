package com.saurabh.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.saurabh.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {


    val breakingNews = newsRepository.getBreakingNews().cachedIn(viewModelScope)
}