package com.priyanka.flickerybs.data.repository

import com.priyanka.flickerybs.data.remote.apiservice.ApiService
import com.priyanka.flickerybs.domain.model.Photo
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(private val apiService: ApiService) : PhotoRepo {

    override suspend fun getRecentPhotos(): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())
        val photos = try {
            apiService.fetchImages().photos.photo
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        }
        photos?.let { listings ->
            val mappedPhotos = listings.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            emit(Resource.Success(mappedPhotos))
        }
    }

    override suspend fun searchPhotos(
        searchText: String,
        userId: String?
    ): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())
        val photos = try {
            apiService.searchPhotos(searchText, userId).photos.photo
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            null
        }
        photos?.let { listings ->
            val mappedPhotos = listings.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            emit(Resource.Success(mappedPhotos))
        }
    }
}


