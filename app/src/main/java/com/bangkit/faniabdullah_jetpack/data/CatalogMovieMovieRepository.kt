package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData

class CatalogMovieMovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogMovieDataSource {
    companion object {
        @Volatile
        private var instance: CatalogMovieMovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogMovieMovieRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogMovieMovieRepository(remoteDataSource)
            }
    }

    override fun getMovieNowPlaying(): LiveData<List<MovieData>> {
        val listMovieNowPlayingResult = MutableLiveData<List<MovieData>>()
        remoteDataSource.getMovieNowPlaying(object :
            RemoteDataSource.LoadMoviesNowPlayingCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse?>) {
                val tvShowList = ArrayList<MovieData>()
                if (movieResponse.isNotEmpty()) {
                    for (response in movieResponse) {
                        if (response !== null) {
                            val tvShow = MovieData(
                                response.id.toString(),
                                response.title,
                                response.originalTitle,
                                response.posterPath,
                                response.overview,
                                response.voteAverage,
                                response.voteCount
                            )
                            tvShowList.add(tvShow)
                        }
                    }
                    listMovieNowPlayingResult.postValue(tvShowList)
                } else {
                    listMovieNowPlayingResult.postValue(tvShowList)
                }
            }
        })

        return listMovieNowPlayingResult
    }

    override fun getPopularTvShows(): LiveData<List<MovieData>> {
        val listTvShowResult = MutableLiveData<List<MovieData>>()
        remoteDataSource.getTvShowPopular(object : RemoteDataSource.LoadPopularTvShowCallback {

            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowsResponse?>) {
                val tvShowList = ArrayList<MovieData>()
                if (tvShowResponse.isNotEmpty()) {
                    for (response in tvShowResponse) {
                        if (response !== null) {
                            val tvShow = MovieData(
                                response.id.toString(),
                                response.name,
                                response.originalName,
                                response.posterPath,
                                response.overview,
                                response.voteAverage,
                                response.voteCount
                            )
                            tvShowList.add(tvShow)
                        }
                    }
                    listTvShowResult.postValue(tvShowList)
                } else {
                    listTvShowResult.postValue(tvShowList)
                }

            }
        })
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