package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData

class DetailViewModel(private val mAcademyMovieRepository: CatalogMovieMovieRepository) :
    ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<DetailMovieData> =
        mAcademyMovieRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvShowId: Int): LiveData<DetailMovieData> =
        mAcademyMovieRepository.getTvShowDetail(
            tvShowId
        )
}