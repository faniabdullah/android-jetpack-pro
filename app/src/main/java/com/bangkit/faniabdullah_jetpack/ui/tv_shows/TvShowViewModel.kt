package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogRepository
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class TvShowViewModel(private val mAcademyRepository: CatalogRepository) : ViewModel() {

    fun getTvShowsPopular(): LiveData<List<MovieData>> = mAcademyRepository.getPopularTvShows()
}