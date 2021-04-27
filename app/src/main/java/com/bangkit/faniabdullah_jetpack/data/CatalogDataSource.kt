package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

interface CatalogDataSource {
    fun getMovieNowPlaying(): LiveData<List<MovieData>>

    fun getPopularTvShows(): LiveData<List<MovieData>>
}