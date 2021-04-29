package com.bangkit.faniabdullah_jetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeCatalogRepository(remote)

    private val moviesNowPlayingResponse = DataDummy.generateDummyDataRemoteNowPlaying()
    private val tvShowsPopularResponse = DataDummy.generateDummyDataRemoteTvShowPopular()

    private val movieId = moviesNowPlayingResponse[0].id.toString()
    private val tvShowId = tvShowsPopularResponse[0].id.toString()

    private val tvShowDetail = DataDummy.generateDummyDataDetailTvShow()
    private val movieDetail = DataDummy.generateDummyDataDetailMovie()

    @Test
    fun testGetMovieNowPlaying() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesNowPlayingCallback).onAllMoviesReceived(
                moviesNowPlayingResponse
            )
            null
        }.`when`(remote).getMovieNowPlaying(any())

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovieNowPlaying())
        verify(remote).getMovieNowPlaying(any())

        assertNotNull(movieEntities)
        assertEquals(moviesNowPlayingResponse.size, movieEntities.size)
    }

    @Test
    fun testGetPopularTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShowCallback).onAllTvShowsReceived(
                tvShowsPopularResponse
            )
            null
        }.`when`(remote).getTvShowPopular(any())

        val data = LiveDataTestUtil.getValue(movieCatalogueRepository.getPopularTvShows())
        verify(remote).getTvShowPopular(any())

        assertNotNull(data)
        assertEquals(tvShowsPopularResponse.size, data.size)
    }

    @Test
    fun testGetMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                movieDetail
            )
            null
        }.`when`(remote).getMovieDetail(eq(movieId.toInt()), any())

        val movieData =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getMovieDetail(movieId.toInt()))
        verify(remote).getMovieDetail(eq(movieId.toInt()), any())
        assertNotNull(movieData)
        assertEquals(movieDetail.id.toString(), movieData.id)
    }

    @Test
    fun testGetTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                tvShowDetail
            )
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId.toInt()), any())

        val tvShowData =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShowDetail(tvShowId.toInt()))
        verify(remote).getTvShowDetail(eq(tvShowId.toInt()), any())
        assertNotNull(tvShowData)
        assertEquals(tvShowDetail.id.toString(), tvShowData.id)
    }
}