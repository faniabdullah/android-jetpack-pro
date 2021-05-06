package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource

class TvShowViewModel(private val mCatalogMovieRepository: CatalogMovieRepository) :
    ViewModel() {

    fun getTvShowsPopular(): LiveData<Resource<PagedList<TvShowsEntity>>> =
        mCatalogMovieRepository.getPopularTvShows()
}