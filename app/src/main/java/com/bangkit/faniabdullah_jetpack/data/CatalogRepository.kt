package com.bangkit.faniabdullah_jetpack.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogDataSource {
    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteDataSource)
            }
    }

    override fun getMovieNowPlaying(): LiveData<List<MovieData>> {
        val listMovieResult = MutableLiveData<List<MovieData>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieNowPlaying(object :
                RemoteDataSource.LoadMoviesNowPlayingCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse?>) {
                    val tvShowList = ArrayList<MovieData>()
                    for (response in movieResponse) {
                        if (response !== null) {
                            val tvShow = MovieData(
                                response.id.toString(),
                                response.title.toString(),
                                response.originalTitle.toString(),
                                response.posterPath.toString(),
                                response.overview.toString(),
                                response.voteAverage.toString(),
                                response.voteCount.toString()
                            )
                            tvShowList.add(tvShow)
                        }
                    }
                    listMovieResult.postValue(tvShowList)
                }
            })
        }
        return listMovieResult
    }

    override fun getPopularTvShows(): LiveData<List<MovieData>> {
        val listTvShowResult = MutableLiveData<List<MovieData>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowPopular(object : RemoteDataSource.LoadPopularTvShowCallback {

                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowsResponse?>) {
                    val tvShowList = ArrayList<MovieData>()
                    for (response in tvShowResponse) {
                        if (response !== null) {
                            val tvShow = MovieData(
                                response.id.toString(),
                                response.name.toString(),
                                response.originalName.toString(),
                                response.posterPath.toString(),
                                response.overview.toString(),
                                response.voteAverage.toString(),
                                response.voteCount.toString()
                            )
                            tvShowList.add(tvShow)
                        }
                    }
                    listTvShowResult.postValue(tvShowList)
                }
            })
        }
        return listTvShowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DetailMovieData> {
        val movieResult = MutableLiveData<DetailMovieData>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieDetailCallback {

                    override fun onMovieDetailReceived(movieResponse: DetailMovieResponse) {
                        val movie = DetailMovieData(
                            movieResponse.id.toString(),
                            movieResponse.originalTitle.toString(),
                            movieResponse.overview.toString(),
                            movieResponse.posterPath.toString(),
                            movieResponse.releaseDate.toString(),
                            movieResponse.voteAverage.toString(),
                            movieResponse.voteCount.toString()
                        )

                        movieResult.postValue(movie)
                    }
                })
        }

        return movieResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DetailMovieData> {
        val detailMovieData = MutableLiveData<DetailMovieData>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId,
                object : RemoteDataSource.LoadTvShowDetailCallback {

                    override fun onTvShowDetailReceived(tvShowResponse: DetailTvResponse) {
                        val movieDetail = DetailMovieData(
                            tvShowResponse.id.toString(),
                            tvShowResponse.originalName as String,
                            tvShowResponse.overview as String,
                            tvShowResponse.posterPath as String,
                            tvShowResponse.firstAirDate.toString(),
                            tvShowResponse.voteAverage.toString(),
                            tvShowResponse.voteCount.toString()
                        )
                        detailMovieData.postValue(movieDetail)
                    }
                })
        }
        return detailMovieData
    }
}