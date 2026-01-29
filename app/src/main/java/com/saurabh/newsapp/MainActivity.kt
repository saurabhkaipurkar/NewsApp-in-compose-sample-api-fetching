package com.saurabh.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.saurabh.newsapp.apiservice.models.NewsArticle
import com.saurabh.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                NewsApp()
            }
        }
    }
}

@Composable
fun NewsApp(viewModel: NewsViewmodel = hiltViewModel()) {
    val newsItems = viewModel.breakingNews.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(newsItems.itemCount) {index->
            NewsCard(newsItems[index])
        }

        newsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    // Show loading indicator
                    item { CircularProgressIndicator() }
                }
                loadState.refresh  is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    // Show error message
                    item {
                        Text(text = error.error.message ?: "Unknown error")
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        val error = loadState.append as LoadState.Error
                        Text(text = error.error.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: NewsArticle?) {
    if (news == null) return

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {

            // ✅ Image
            news.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = news.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // ✅ Text content
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium
                )

                news.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Text(
                    text = news.sourceName ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {

    }
}