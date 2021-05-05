package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import com.bangkit.faniabdullah_jetpack.data.source.local.LocalDataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.vo.ApiResponse
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

    override fun getPopularTvShows(): LiveData<Resource<List<TvShowsEntity>>> {
        return object :
            NetworkBoundResource<List<TvShowsEntity>, List<TvShowsResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<TvShowsEntity>> =
                localDataSource.getAllTvShowsPopular()

            override fun shouldFetch(data: List<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowsResponse>>> =
                remoteDataSource.getTvShowPopular()

            public override fun saveCallResult(data: List<TvShowsResponse>) {
                val movieList = ArrayList<TvShowsEntity>()

                for (response in data) {
                    val course = response.id?.let {
                        TvShowsEntity(
                            it,
                            response.originalName,
                            response.overview,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage,
                            response.voteCount
                        )
                    }
                    if (course != null) {
                        movieList.add(course)
                    }
                }

                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getMovieById(movieId)

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowsEntity> =
        localDataSource.getTvShowsById(tvShowId)

    override fun setFavoriteTvShows(tvShow: TvShowsEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShows(tvShow, state) }
    }

    override fun getFavoritesTvShows(): LiveData<List<TvShowsEntity>> =
        localDataSource.getAllFavoritesTvShows()

    override fun setFavoriteMovies(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    }

    override fun getFavoritesMovies(): LiveData<List<MovieEntity>> =
        localDataSource.getAllFavoritesMovie()
}