package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.faniabdullah_jetpack.data.source.local.LocalDataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.DetailMovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.DetailTvResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.vo.ApiResponse
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
import com.bangkit.faniabdullah_jetpack.domain.model.MovieData
import com.bangkit.faniabdullah_jetpack.utils.AppExecutors
import com.bangkit.faniabdullah_jetpack.utils.vo.Resource

class CatalogMovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogMovieDataSource {
    companion object {
        @Volatile
        private var instance: CatalogMovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogMovieRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogMovieRepository(
                    remoteDataSource,
                    localData,
                    appExecutors
                ).apply {
                    instance = this
                }
            }
    }

    override fun getMovieNowPlaying(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<MovieEntity>> =
                localDataSource.getAllMoviesNowPlaying()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieNowPlaying()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val course = response.id?.let {
                        MovieEntity(
                            it,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage,
                            response.voteCount
                        )
                    }
                    if (course != null) {
                        movieList.add(course)
                    }
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
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