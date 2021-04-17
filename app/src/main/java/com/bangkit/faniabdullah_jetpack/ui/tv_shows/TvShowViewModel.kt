package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShowsPopularNewsVersion(): List<MovieEntity> =
        DataDummy.generateDummyDataTvShowsPopular()
}