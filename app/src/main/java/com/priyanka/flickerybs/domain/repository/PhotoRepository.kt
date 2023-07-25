package com.priyanka.flickerybs.domain.repository

import com.priyanka.flickerybs.data.local.entity.FavoritePhotoListingEntity
import com.priyanka.flickerybs.data.local.entity.PhotoListingEntity
import com.priyanka.flickerybs.data.remote.dto.FlickerResponse
import com.priyanka.flickerybs.domain.model.PhotoDetail
import com.priyanka.flickerybs.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotoListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<PhotoDetail>>>

    suspend fun getPhotoListingFromDb(
        query: String
    ): List<PhotoListingEntity>

    suspend fun getPhotoListingFromApi(): FlickerResponse

    suspend fun clearPhotoListingsFromDb()

    suspend fun insertPhotoListingToDb(
        showList: List<PhotoListingEntity>
    )
    suspend fun getPhotoInfo(query: String):Resource<PhotoDetail>

    suspend fun insertFavoritePhotoToDb(photo: PhotoDetail)

    suspend fun getFavorites():Flow<Resource<List<PhotoDetail>>>

    suspend fun deleteFavoriteById(favoriteId: String)

    suspend fun getAllFavoritesFromDb(): List<FavoritePhotoListingEntity>

    suspend fun getSinglePhotoFromDB(query: String): PhotoListingEntity
}

