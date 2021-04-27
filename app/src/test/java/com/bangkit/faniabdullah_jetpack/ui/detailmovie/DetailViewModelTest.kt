package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
        assertNotNull(movie)
        assertEquals(dummyMovieNowPlaying.id, movie.id)
        assertEquals(dummyMovieNowPlaying.original_title, movie.original_title)
        assertEquals(dummyMovieNowPlaying.overview, movie.overview)
        assertEquals(dummyMovieNowPlaying.poster_path, movie.poster_path)
        assertEquals(dummyMovieNowPlaying.vote_average.toString(), movie.vote_average.toString())
        assertEquals(dummyMovieNowPlaying.vote_count.toString(), movie.vote_count.toString())
    }

    @Test
    fun testGetDetailTvShowById() {
        mainViewModel.setSelectedMovie(tvShowId)
        val movie = mainViewModel.getDetailTvShowById()
        assertNotNull(movie)
        assertEquals(dummyTvShowPopular.id, movie.id)
        assertEquals(dummyTvShowPopular.id, movie.id)
        assertEquals(dummyTvShowPopular.original_title, movie.original_title)
        assertEquals(dummyTvShowPopular.overview, movie.overview)
        assertEquals(dummyTvShowPopular.poster_path, movie.poster_path)
        assertEquals(dummyTvShowPopular.vote_average.toString(), movie.vote_average.toString())
        assertEquals(dummyTvShowPopular.vote_count.toString(), movie.vote_count.toString())
    }
}