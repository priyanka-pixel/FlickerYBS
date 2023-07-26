package com.priyanka.flickerybs.data.remote.dto

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val tags: String,
    val description: String
)
