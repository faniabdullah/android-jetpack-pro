package com.bangkit.faniabdullah_jetpack.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "tb_tvshows")
data class TvShowsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowsId")
    val tvshows_id: String? = null,
    @ColumnInfo(name = "title")
    val original_title: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "poster_path")
    val poster_path: String? = null,
    @ColumnInfo(name = "release_date")
    val release_date: String? = null,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double? = null,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int? = null,
    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean? = false,
) : Parcelable