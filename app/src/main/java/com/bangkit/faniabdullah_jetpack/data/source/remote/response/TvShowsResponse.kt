package com.bangkit.faniabdullah_jetpack.data.source.remote.response

import android.os.Parcelable
import com.bangkit.faniabdullah_jetpack.data.source.local.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsResponse(
    val results: ArrayList<MovieEntity>
) : Parcelable