package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var mainViewModel: DetailViewModel
    private val dummyMovieNowPlaying = DataDummy.generateDummyDataMovieNowPlaying()[0]
    private val dummyTvShowPopular = DataDummy.generateDummyDataTvShowsPopular()[0]

    private val dummyDataDetailMovie = MovieEntity(
        dummyMovieNowPlaying.movie_id,
        dummyMovieNowPlaying.original_title,
        dummyMovieNowPlaying.overview,
        dummyMovieNowPlaying.poster_path,
        dummyMovieNowPlaying.release_date,
        dummyMovieNowPlaying.vote_average,
        dummyMovieNowPlaying.vote_count
    )

    private val dummyDataDetailTvShows = TvShowsEntity(
        dummyTvShowPopular.tvShows_id,
        dummyTvShowPopular.original_title,
        dummyTvShowPopular.overview,
        dummyTvShowPopular.poster_path,
        dummyTvShowPopular.release_date,
        dummyTvShowPopular.vote_average,
        dummyTvShowPopular.vote_count
    )

    private val movieId = dummyMovieNowPlaying.movie_id
    private val tvShowId = dummyTvShowPopular.tvShows_id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueMovieRepository: CatalogMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvObserver: Observer<TvShowsEntity>

    @Before
    fun setUpMovie() {
        mainViewModel = DetailViewModel(catalogueMovieRepository)
    }

    @Test
    fun testGetDetailMovieById() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyDataDetailMovie

        `when`(movieId.let { catalogueMovieRepository.getMovieDetail(it) }).thenReturn(movie)
        val detailData =
            movieId.let { mainViewModel.getDetailMovieById(it).value } as MovieEntity
        verify(catalogueMovieRepository).getMovieDetail(movieId)

        assertNotNull(detailData)
        assertEquals(dummyMovieNowPlaying.original_title, detailData.original_title)
        assertEquals(dummyMovieNowPlaying.movie_id, detailData.movie_id)
        assertEquals(dummyMovieNowPlaying.overview, detailData.overview)
        assertEquals(dummyMovieNowPlaying.poster_path, detailData.poster_path)
        assertEquals(dummyMovieNowPlaying.vote_count, detailData.vote_count)
        assertEquals(dummyMovieNowPlaying.release_date, detailData.release_date)
        assertEquals(dummyMovieNowPlaying.vote_average, detailData.vote_average)

        mainViewModel.getDetailMovieById(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDataDetailMovie)
    }

    @Test
    fun testGetDetailTvShowById() {
        val movie = MutableLiveData<TvShowsEntity>()
        movie.value = dummyDataDetailTvShows

        `when`(tvShowId.let { catalogueMovieRepository.getTvShowDetail(it) }).thenReturn(movie)
        val detailData =
            tvShowId.let { mainViewModel.getDetailTvShowById(it).value } as TvShowsEntity
        verify(catalogueMovieRepository).getTvShowDetail(tvShowId)

        assertNotNull(detailData)
        assertEquals(dummyTvShowPopular.original_title, detailData.original_title)
        assertEquals(dummyTvShowPopular.tvShows_id, detailData.tvShows_id)
        assertEquals(dummyTvShowPopular.overview, detailData.overview)
        assertEquals(dummyTvShowPopular.poster_path, detailData.poster_path)
        assertEquals(dummyTvShowPopular.vote_count, detailData.vote_count)
        assertEquals(dummyTvShowPopular.release_date, detailData.release_date)
        assertEquals(dummyTvShowPopular.vote_average, detailData.vote_average)

        mainViewModel.getDetailTvShowById(tvShowId).observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyDataDetailTvShows)
    }
}