package com.bangkit.faniabdullah_jetpack.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowsEntity(
    val id: Int,
    val original_name : String,
    val overview : String,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable