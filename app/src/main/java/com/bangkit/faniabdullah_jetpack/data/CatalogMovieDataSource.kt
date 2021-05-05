package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource

interface CatalogMovieDataSource {
    fun getMovieNowPlaying(): LiveData<Resource<List<MovieEntity>>>

    fun getPopularTvShows(): LiveData<List<MovieData>>

    fun getMovieDetail(movieId: Int): LiveData<DetailMovieData>

    fun getTvShowDetail(tvShowId: Int): LiveData<DetailMovieData>
}