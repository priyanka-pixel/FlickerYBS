package com.priyanka.flickerybs.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.priyanka.flickerybs.data.remote.apiservice.ApiService
import com.priyanka.flickerybs.data.repository.FlickrRepositoryImpl
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val CONNECTION_TIMEOUT_MS: Long = 10

    @Provides
    @Singleton
    fun provideFlickrRepository(apiService: ApiService): PhotoRepo {
        return FlickrRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

}
