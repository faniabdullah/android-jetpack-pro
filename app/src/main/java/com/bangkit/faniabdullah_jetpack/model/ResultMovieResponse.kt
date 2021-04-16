package com.bangkit.faniabdullah_jetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultMovieResponse(
    val results: ArrayList<MovieEntity>
):Parcelable
