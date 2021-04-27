package com.bangkit.faniabdullah_jetpack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovieData(
    val id: String? = null,
    val original_title: String? = null,
    val overview:  String? = null,
    val poster_path:  String? = null,
    val release_date: String? = null,
    val vote_average:  Double? = null,
    val vote_count:  Int? = null,
) : Parcelable