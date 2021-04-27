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
        remoteDataSource.getMovieNowPlaying(object :
            RemoteDataSource.LoadMoviesNowPlayingCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse?>) {
                val tvShowList = ArrayList<MovieData>()
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
                    listMovieResult.postValue(tvShowList)
                }
            }
        })

        return listMovieResult
    }

    override fun getPopularTvShows(): LiveData<List<MovieData>> {
        val listTvShowResult = MutableLiveData<List<MovieData>>()
        remoteDataSource.getTvShowPopular(object : RemoteDataSource.LoadPopularTvShowCallback {

            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowsResponse?>) {
                val tvShowList = ArrayList<MovieData>()
                if (tvShowResponse.isNotEmpty()){
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
                        listTvShowResult.postValue(tvShowList)
                    }
                }else{
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