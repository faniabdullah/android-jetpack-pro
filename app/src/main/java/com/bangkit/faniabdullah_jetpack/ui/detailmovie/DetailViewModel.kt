package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogRepository
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData

class DetailViewModel(private val mAcademyRepository: CatalogRepository) : ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<DetailMovieData> =
        mAcademyRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvShowId: Int): LiveData<DetailMovieData> =
        mAcademyRepository.getTvShowDetail(
            tvShowId
        )
}