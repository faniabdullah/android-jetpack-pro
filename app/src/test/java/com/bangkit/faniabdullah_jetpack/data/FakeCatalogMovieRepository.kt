package com.bangkit.faniabdullah_jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bangkit.faniabdullah_jetpack.data.source.local.LocalDataSource
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.data.source.remote.RemoteDataSource
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.movie.MovieResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.tvshows.TvShowsResponse
import com.bangkit.faniabdullah_jetpack.data.source.remote.response.vo.ApiResponse
import com.bangkit.faniabdullah_jetpack.utils.AppExecutors
import com.bangkit.faniabdullah_jetpack.vo.Resource

class FakeCatalogMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogMovieDataSource {

    override fun getMovieNowPlaying(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllMoviesNowPlaying(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieNowPlaying()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = response.id?.let {
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
                    if (movie != null) {
                        movieList.add(movie)
                    }
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShowsResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShowsPopular(), config).build()
            }


            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowsResponse>>> =
                remoteDataSource.getTvShowPopular()

            public override fun saveCallResult(data: List<TvShowsResponse>) {
                val movieList = ArrayList<TvShowsEntity>()

                for (response in data) {
                    val movie = response.id?.let {
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
                    if (movie != null) {
                        movieList.add(movie)
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
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShows(tvShow, state)
        }
    }

    override fun getFavoritesTvShows(): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoritesTvShows(), config).build()
    }

    override fun setFavoriteMovies(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getFavoritesMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoritesMovie(), config).build()
    }
}