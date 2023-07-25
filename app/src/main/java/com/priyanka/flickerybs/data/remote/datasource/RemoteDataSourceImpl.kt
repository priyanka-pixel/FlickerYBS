package com.priyanka.flickerybs.data.remote.datasource

import com.priyanka.flickerybs.data.remote.apiservice.ApiService
import com.priyanka.flickerybs.data.remote.dto.FlickerResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
): RemoteDatasource
{
    override suspend fun getPhotoListingFromApi(): FlickerResponse =api.getRecentPhotos()
}