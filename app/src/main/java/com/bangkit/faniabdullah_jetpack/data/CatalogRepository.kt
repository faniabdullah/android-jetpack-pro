package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.TvShowsResponse
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
}