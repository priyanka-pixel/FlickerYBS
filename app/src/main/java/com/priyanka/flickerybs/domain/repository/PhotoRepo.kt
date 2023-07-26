package com.priyanka.flickerybs.domain.repository

import com.priyanka.flickerybs.domain.model.Photo
import com.priyanka.flickerybs.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoRepo {
    suspend fun getRecentPhotos(): Flow<Resource<List<Photo>>>
    suspend fun searchPhotos(
        searchText: String,
        userId: String? = null
    ): Flow<Resource<List<Photo>>>
}
