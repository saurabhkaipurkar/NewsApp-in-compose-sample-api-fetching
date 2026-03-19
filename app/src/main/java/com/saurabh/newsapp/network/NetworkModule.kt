package com.saurabh.newsapp.network

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.saurabh.newsapp.apiservice.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://newsdata.io/"

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp) {

        install(ContentNegotiation) {
            gson()
        }

        install(Logging) {
            level = LogLevel.BODY
        }

        engine {
            config {
                connectTimeout(15.seconds)
                readTimeout(20.seconds)
                writeTimeout(20.seconds)
                retryOnConnectionFailure(true)
            }
        }

        defaultRequest {
            url(BASE_URL)
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService = ApiService(client)
}