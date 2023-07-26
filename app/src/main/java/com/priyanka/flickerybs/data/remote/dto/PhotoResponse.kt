package com.priyanka.flickerybs.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val tags: String,

)
