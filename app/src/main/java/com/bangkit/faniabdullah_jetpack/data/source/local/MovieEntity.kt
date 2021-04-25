package com.bangkit.faniabdullah_jetpack.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable