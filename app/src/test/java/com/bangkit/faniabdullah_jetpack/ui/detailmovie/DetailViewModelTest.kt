package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var mainViewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyDataMovieNowPlaying()[0]
    private val dummyTvShow = DataDummy.generateDummyDataTvShowsPopular()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        mainViewModel = DetailViewModel()
    }

    @Test
    fun testGetDetailMovieById() {
        mainViewModel.setSelectedMovie(movieId)
        val movie = mainViewModel.getDetailMovieById()
        assertEquals(dummyMovie.id, movie.id)
    }

    @Test
    fun testGetDetailTvShowById() {
        mainViewModel.setSelectedMovie(tvShowId)
        val movie = mainViewModel.getDetailTvShowById()
        assertEquals(dummyTvShow.id, movie.id)
    }
}