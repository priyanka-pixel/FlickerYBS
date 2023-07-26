package com.priyanka.flickerybs.presentation.search

import com.priyanka.flickerybs.domain.model.Photo


data class SearchPhotoState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val id: String = "",
    val photo: Photo = Photo(id = "", url = "", title = ""),
    val error: String = ""
)
