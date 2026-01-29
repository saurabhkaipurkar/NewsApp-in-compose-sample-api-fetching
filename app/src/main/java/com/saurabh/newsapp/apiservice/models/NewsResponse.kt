package com.saurabh.newsapp.apiservice.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("results")
    val results: List<NewsArticle>,

    @SerializedName("nextPage")
    val nextPage: String?
)

data class  NewsArticle(
    @SerializedName("article_id")
    val articleId: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("keywords")
    val keywords: List<String>?,

    @SerializedName("creator")
    val creator: List<String>?,

    @SerializedName("language")
    val language: String,

    @SerializedName("country")
    val country: List<String>,

    @SerializedName("category")
    val category: List<String>,

    @SerializedName("datatype")
    val datatype: String,

    @SerializedName("pubDate")
    val pubDate: String,

    @SerializedName("pubDateTZ")
    val pubDateTZ: String,

    @SerializedName("fetched_at")
    val fetchedAt: String,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("video_url")
    val videoUrl: String?,

    @SerializedName("source_id")
    val sourceId: String,

    @SerializedName("source_name")
    val sourceName: String,

    @SerializedName("source_priority")
    val sourcePriority: Int,

    @SerializedName("source_url")
    val sourceUrl: String,

    @SerializedName("source_icon")
    val sourceIcon: String,

    @SerializedName("sentiment")
    val sentiment: String?,

    @SerializedName("sentiment_stats")
    val sentimentStats: String?,

    @SerializedName("ai_tag")
    val aiTag: String?,

    @SerializedName("ai_region")
    val aiRegion: String?,

    @SerializedName("ai_org")
    val aiOrg: String?,

    @SerializedName("ai_summary")
    val aiSummary: String?,

    @SerializedName("duplicate")
    val duplicate: Boolean
)

