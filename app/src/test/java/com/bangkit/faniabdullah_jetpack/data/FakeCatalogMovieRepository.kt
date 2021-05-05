package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class FakeCatalogMovieRepository(private val remoteDataSource: RemoteDataSource) :
    CatalogMovieDataSource {

    override fun getMovieNowPlaying(): LiveData<List<MovieData>> {
        val listMovieNowPlayingResult = MutableLiveData<List<MovieData>>()
        remoteDataSource.getMovieNowPlaying()

        return listMovieNowPlayingResult
    }

    override fun getPopularTvShows(): LiveData<List<MovieData>> {
        val listTvShowResult = MutableLiveData<List<MovieData>>()
        remoteDataSource.getTvShowPopular()
        return listTvShowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DetailMovieData> {
        val movieResult = MutableLiveData<DetailMovieData>()
        remoteDataSource.getMovieDetail(
            movieId,
            object : RemoteDataSource.LoadMovieDetailCallback {

                override fun onMovieDetailReceived(movieResponse: DetailMovieResponse) {
                    val movie = DetailMovieData(
                        movieResponse.id.toString(),
                        movieResponse.originalTitle,
                        movieResponse.overview,
                        movieResponse.posterPath,
                        movieResponse.releaseDate,
                        movieResponse.voteAverage,
                        movieResponse.voteCount
                    )

                    movieResult.postValue(movie)
                }
            })

        return movieResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DetailMovieData> {
        val detailMovieData = MutableLiveData<DetailMovieData>()

        remoteDataSource.getTvShowDetail(tvShowId,
            object : RemoteDataSource.LoadTvShowDetailCallback {

                override fun onTvShowDetailReceived(tvShowResponse: DetailTvResponse) {
                    val movieDetail = DetailMovieData(
                        tvShowResponse.id.toString(),
                        tvShowResponse.originalName,
                        tvShowResponse.overview,
                        tvShowResponse.posterPath,
                        tvShowResponse.firstAirDate,
                        tvShowResponse.voteAverage,
                        tvShowResponse.voteCount
                    )
                    detailMovieData.postValue(movieDetail)
                }
            })
        return detailMovieData
    }
}