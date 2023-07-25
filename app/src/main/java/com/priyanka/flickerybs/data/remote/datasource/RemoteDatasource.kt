package com.priyanka.flickerybs.data.remote.datasource

import com.priyanka.flickerybs.data.remote.dto.FlickerResponse

interface RemoteDatasource {
    suspend fun getPhotoListingFromApi(): FlickerResponse
}