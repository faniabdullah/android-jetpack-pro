package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

class DetailViewModel(private val mAcademyMovieRepository: CatalogMovieRepository) :
    ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity> =
        mAcademyMovieRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvShowId: Int): LiveData<TvShowsEntity> =
        mAcademyMovieRepository.getTvShowDetail(tvShowId)
}