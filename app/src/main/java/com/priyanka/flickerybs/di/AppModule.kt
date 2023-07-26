package com.priyanka.flickerybs.di

import com.priyanka.flickerybs.data.remote.apiservice.ApiService
import com.priyanka.flickerybs.data.repository.FlickrRepositoryImpl
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiService.BASE_URL)
        .build()

    @Provides
    fun providesApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    fun providesRepository(
        apiService: ApiService,
    ): PhotoRepo {
        return FlickrRepositoryImpl(apiService)
    }
}
