package com.priyanka.flickerybs.data.remote.apiservice

import com.priyanka.flickerybs.data.remote.apiservice.Keys.FLICKR_API_KEY
import com.priyanka.flickerybs.data.remote.dto.FlickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.getRecent&api_key=${FLICKR_API_KEY}&tags=dogs&format=json&nojsoncallback=1")
    suspend fun fetchImages(): FlickerResponse

    @GET("?method=flickr.photos.search&api_key=${FLICKR_API_KEY}&format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("text") searchText: String // Search text (e.g., tags, title, etc.)
    ): FlickerResponse

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/rest/"

    }
}