package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var mainViewModel: DetailViewModel
    private val dummyMovieNowPlaying = DataDummy.generateDummyDataMovieNowPlaying()[0]
    private val dummyTvShowPopular = DataDummy.generateDummyDataTvShowsPopular()[0]
    private val movieId = dummyMovieNowPlaying.id
    private val tvShowId = dummyTvShowPopular.id

    @Before
    fun setUp() {
        mainViewModel = DetailViewModel()
    }

    @Test
    fun testGetDetailMovieById() {
        mainViewModel.setSelectedMovie(movieId)
        val movie = mainViewModel.getDetailMovieById()
        assertEquals(dummyMovieNowPlaying.id, movie.id)
    }

    @Test
    fun testGetDetailTvShowById() {
        mainViewModel.setSelectedMovie(tvShowId)
        val movie = mainViewModel.getDetailTvShowById()
        assertEquals(dummyTvShowPopular.id, movie.id)
    }
}