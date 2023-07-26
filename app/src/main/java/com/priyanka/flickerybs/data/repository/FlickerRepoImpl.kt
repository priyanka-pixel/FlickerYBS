package com.priyanka.flickerybs.data.repository

import com.priyanka.flickerybs.data.remote.apiservice.ApiService
import com.priyanka.flickerybs.domain.model.Photo
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FlickrRepositoryImpl(private val apiService: ApiService) : PhotoRepo {
    override suspend fun getRecentPhotos(): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())
        try {
            val photos = apiService.fetchImages().photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title,
                    tags = photo.tags
                )
            }
            emit(Resource.Success(photos))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to get recent photos."))
        }
    }.catch { e ->
        emit(Resource.Error("An error occurred: ${e.message}"))
    }.flowOn(Dispatchers.IO)

    override suspend fun searchPhotos(searchText: String, userId: String?): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())
        try {
            val photos = apiService.searchPhotos(searchText, userId).photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title,
                    tags = photo.tags
                )
            }
            emit(Resource.Success(photos))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to search photos."))
        }
    }.catch { e ->
        emit(Resource.Error("An error occurred: ${e.message}"))
    }.flowOn(Dispatchers.IO)
}
