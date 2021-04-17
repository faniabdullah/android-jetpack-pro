package com.bangkit.faniabdullah_jetpack.ui.movie

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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
        assertEquals(11, movieEntities.size)
    }
}