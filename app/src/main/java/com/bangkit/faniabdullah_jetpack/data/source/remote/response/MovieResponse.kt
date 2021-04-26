package com.bangkit.faniabdullah_jetpack.data.source.remote.response

import com.bangkit.faniabdullah_jetpack.data.source.local.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse<T>(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<MovieEntity>? = null
)