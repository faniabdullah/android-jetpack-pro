package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieRepository
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

class DetailViewModel(private val mCatalogMovieRepository: CatalogMovieRepository) :
    ViewModel() {

    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity> =
        mCatalogMovieRepository.getMovieDetail(movieId)

    fun getDetailTvShowById(tvShowId: Int): LiveData<TvShowsEntity> =
        mCatalogMovieRepository.getTvShowDetail(tvShowId)

    fun setBookmarkedTvShow(tvShow: TvShowsEntity) {
        val newState = !tvShow.bookmarked
        mCatalogMovieRepository.setBookmarkedTvShow(tvShow, newState)
    }

    fun setBookmarkedMovies(movie: MovieEntity) {
        val newState = !movie.bookmarked
        mCatalogMovieRepository.setBookmarkedMovie(movie, newState)
    }
}