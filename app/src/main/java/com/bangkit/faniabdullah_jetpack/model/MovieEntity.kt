package com.bangkit.faniabdullah_jetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable
