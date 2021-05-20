package com.bangkit.faniabdullah_jetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bangkit.faniabdullah_jetpack.TestExecutor
import com.bangkit.faniabdullah_jetpack.data.source.local.LocalDataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.room.MovieDao
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.utils.AppExecutors
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import com.bangkit.faniabdullah_jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val dao = mock(MovieDao::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeCatalogMovieRepository(remote, local, appExecutors)

    private val moviesNowPlayingResponse = DataDummy.generateDummyDataRemoteNowPlaying()
    private val tvShowsPopularResponse = DataDummy.generateDummyDataRemoteTvShowPopular()

    private val tvShowDetail = DataDummy.generateDummyDataTvShowsPopular()[0]
    private val movieDetail = DataDummy.generateDummyDataMovieNowPlaying()[0]

    @Test
    fun testGetMovieNowPlaying() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMoviesNowPlaying()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getMovieNowPlaying()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataMovieNowPlaying()))
        verify(local).getAllMoviesNowPlaying()
        assertNotNull(courseEntities.data)
        assertEquals(moviesNowPlayingResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun testGetPopularTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getAllTvShowsPopular()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getPopularTvShows()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataTvShowsPopular()))
        verify(local).getAllTvShowsPopular()
        assertNotNull(courseEntities.data)
        assertEquals(tvShowsPopularResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun testGetMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieDetail
        `when`(local.getMovieById(movieDetail.movie_id)).thenReturn(dummyMovie)

        val data =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getMovieDetail(movieDetail.movie_id))
        verify(local).getMovieById(movieDetail.movie_id)
        assertNotNull(data)
        assertEquals(movieDetail.movie_id, data.movie_id)
    }

    @Test
    fun testGetTvShowDetail() {
        val dummyMovie = MutableLiveData<TvShowsEntity>()
        dummyMovie.value = tvShowDetail
        `when`(local.getTvShowsById(tvShowDetail.tvShows_id)).thenReturn(dummyMovie)

        val data =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShowDetail(tvShowDetail.tvShows_id))
        verify(local).getTvShowsById(tvShowDetail.tvShows_id)
        assertNotNull(data)
        assertEquals(tvShowDetail.tvShows_id, data.tvShows_id)
    }

    @Test
    fun testGetFavoritesTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getAllFavoritesTvShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoritesTvShows()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataTvShowsPopular()))
        verify(local).getAllFavoritesTvShows()
        assertNotNull(courseEntities)
        assertEquals(tvShowsPopularResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun testGetFavoritesMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoritesMovie()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoritesMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataMovieNowPlaying()))
        verify(local).getAllFavoritesMovie()
        assertNotNull(movieEntities)
        assertEquals(moviesNowPlayingResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }


    @Test
    fun setMovieFavorite() {
        val localDataSource = LocalDataSource(dao)
        val dataDummy = DataDummy.generateDummyDataMovieNowPlaying()[0]
        val expectedDataDummy = dataDummy.copy(favorite = true)
        val testExecutors = AppExecutors(TestExecutor(), TestExecutor() , TestExecutor())

        `when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        doNothing().`when`(local).setFavoriteMovie(dataDummy, true)
        movieCatalogueRepository.setFavoriteMovies(dataDummy, true)
        verify(local).setFavoriteMovie(dataDummy, true)

        doNothing().`when`(dao).updateMovie(expectedDataDummy)
        localDataSource.setFavoriteMovie(dataDummy, true)
        verify(dao, times(1)).updateMovie(expectedDataDummy)
    }

    @Test
    fun setTvShowsFavorite() {
        val localDataSource = LocalDataSource(dao)
        val dataDummy = DataDummy.generateDummyDataTvShowsPopular()[0]
        val expectedDataDummy = dataDummy.copy(favorite = true)

        val testExecutors = AppExecutors(TestExecutor(), TestExecutor() , TestExecutor())

        `when`(appExecutors.diskIO()).thenReturn(testExecutors.diskIO())
        doNothing().`when`(local).setFavoriteTvShows(dataDummy, true)
        movieCatalogueRepository.setFavoriteTvShows(dataDummy, true)
        verify(local).setFavoriteTvShows(dataDummy, true)

        doNothing().`when`(dao).updateTvShows(expectedDataDummy)
        localDataSource.setFavoriteTvShows(dataDummy, true)
        verify(dao, times(1)).updateTvShows(expectedDataDummy)
    }
}