package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieMovieRepository
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var mainViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueMovieRepository: CatalogMovieMovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieData>>

    @Before
    fun setUp() {
        mainViewModel = MovieViewModel(movieCatalogueMovieRepository)
    }

    @Test
    fun testGetMovieNowPlaying() {
        val dummyMovies = DataDummy.generateDummyDataMovieNowPlaying()
        val movies = MutableLiveData<List<MovieData>>()
        movies.value = dummyMovies

        `when`(movieCatalogueMovieRepository.getMovieNowPlaying()).thenReturn(movies)

        val movie = mainViewModel.getMovieNowPlaying().value
        verify(movieCatalogueMovieRepository).getMovieNowPlaying()
        assertNotNull(movie)
        assertEquals(12, movie?.size)

        mainViewModel.getMovieNowPlaying().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}