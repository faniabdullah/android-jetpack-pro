package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.vo.Resource
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
    private lateinit var mainViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueMovieRepository: CatalogMovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        mainViewModel = TvShowViewModel(catalogueMovieRepository)
    }

    @Test
    fun testGetTvShowsPopular() {

        val dummyMovies = Resource.success(moviePagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(4)
        val movie = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        movie.value = dummyMovies

        Mockito.`when`(catalogueMovieRepository.getPopularTvShows()).thenReturn(movie)

        val movieEntity = mainViewModel.getTvShowsPopular().value?.data
        Mockito.verify(catalogueMovieRepository).getPopularTvShows()

        assertNotNull(movieEntity)
        assertEquals(4, movieEntity?.size)

        mainViewModel.getTvShowsPopular().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}