package com.bangkit.faniabdullah_jetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueMovieRepository: CatalogMovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueMovieRepository)
    }

    @Test
    fun testGetMovieNowPlaying() {

        val dummyMovies = Resource.success(moviePagedList)
        `when`(dummyMovies.data?.size).thenReturn(4)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovies

        `when`(catalogueMovieRepository.getMovieNowPlaying()).thenReturn(movie)

        val movieEntity = viewModel.getMovieNowPlaying().value?.data
        Mockito.verify(catalogueMovieRepository).getMovieNowPlaying()

        assertNotNull(movieEntity)
        assertEquals(4, movieEntity?.size)

        viewModel.getMovieNowPlaying().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }

}