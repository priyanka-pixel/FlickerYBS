package com.priyanka.flickerybs.presentation.home

import com.priyanka.flickerybs.domain.model.Photo

data class PhotosState(
    val photos: List<Photo> = emptyList(),
    val isLoading:Boolean =false,
    val error:String=""
    )
