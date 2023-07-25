package com.priyanka.flickerybs.data.remote.apiservice

import com.priyanka.flickerybs.data.remote.dto.FlickerResponse
import com.priyanka.flickerybs.domain.model.PhotoDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.getRecent&api_key=${keys.FLICKR_API_KEY}&format=json&nojsoncallback=1")
    suspend fun getRecentPhotos(): FlickerResponse

    @GET("?method=flickr.photos.search&api_key=${keys.FLICKR_API_KEY}&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("text") searchText: String, // Search text (e.g., tags, title, etc.)
        @Query("user_id") userId: String? = null // Optional parameter for filtering by user
    ): FlickerResponse

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/rest/"

    }
}