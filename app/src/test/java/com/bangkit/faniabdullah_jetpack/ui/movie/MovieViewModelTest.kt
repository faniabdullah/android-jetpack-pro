package com.bangkit.faniabdullah_jetpack.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var mainViewModel: MovieViewModel

    @Before
    fun init() {
        mainViewModel = MovieViewModel()
    }

    @Test
    fun testGetMovieNowPlaying() {
        val movieEntities = mainViewModel.getMovieNowPlaying()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.size)
    }
}