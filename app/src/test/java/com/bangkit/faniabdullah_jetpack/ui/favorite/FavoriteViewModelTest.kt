package com.bangkit.faniabdullah_jetpack.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var mainViewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueMovieRepository: CatalogMovieRepository

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<TvShowsEntity>>

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        mainViewModel = FavoriteViewModel(catalogueMovieRepository)
    }

    @Test
    fun testGetFavoriteMovie() {

        val dummyMovies = moviePagedList
        Mockito.`when`(dummyMovies.size).thenReturn(4)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(catalogueMovieRepository.getFavoritesMovies()).thenReturn(movie)

        val movieEntity = mainViewModel.getFavoriteMovie().value
        Mockito.verify(catalogueMovieRepository).getFavoritesMovies()

        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(4, movieEntity?.size)

        mainViewModel.getFavoriteMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun testGetFavoriteTvShows() {
        val dummyMovies = tvShowsPagedList
        Mockito.`when`(dummyMovies.size).thenReturn(4)
        val movie = MutableLiveData<PagedList<TvShowsEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(catalogueMovieRepository.getFavoritesTvShows()).thenReturn(movie)

        val tvShowsEntity = mainViewModel.getFavoriteTvShows().value
        Mockito.verify(catalogueMovieRepository).getFavoritesTvShows()

        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(4, tvShowsEntity?.size)

        mainViewModel.getFavoriteTvShows().observeForever(observerTvShows)
        Mockito.verify(observerTvShows).onChanged(dummyMovies)
    }
}