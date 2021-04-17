package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var mainViewModel: TvShowViewModel


    @Before
    fun init() {
        mainViewModel = TvShowViewModel()
    }

    @Test
    fun testGetTvShowsPopularNewsVersion() {
        val movieEntities = mainViewModel.getTvShowsPopularNewsVersion()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.size)
    }
}