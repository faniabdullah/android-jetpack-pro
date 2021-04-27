package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bangkit.faniabdullah_jetpack.data.CatalogRepository
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<MovieData>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetTvShowsPopular() {
        val dummyMovies = DataDummy.generateDummyDataTvShowsPopular()
        val movies = MutableLiveData<List<MovieData>>()
        movies.value = dummyMovies

        Mockito.`when`(movieCatalogueRepository.getPopularTvShows()).thenReturn(movies)

        val movie = viewModel.getTvShowsPopular().value
        verify(movieCatalogueRepository).getPopularTvShows()
        assertNotNull(movie)
        assertEquals(12, movie?.size)

        viewModel.getTvShowsPopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}