package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

interface CatalogMovieDataSource {
    fun getMovieNowPlaying(): LiveData<List<MovieData>>

    fun getPopularTvShows(): LiveData<List<MovieData>>

    fun getMovieDetail(movieId: Int): LiveData<DetailMovieData>

    fun getTvShowDetail(tvShowId: Int): LiveData<DetailMovieData>
}