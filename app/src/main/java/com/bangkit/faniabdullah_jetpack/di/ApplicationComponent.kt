package com.bangkit.faniabdullah_jetpack.di

import android.content.Context
import com.bangkit.faniabdullah_jetpack.data.CatalogMovieDataSource
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.ui.favorite.FavoriteFragment
import com.bangkit.faniabdullah_jetpack.ui.movie.MovieFragment
import com.bangkit.faniabdullah_jetpack.ui.tv_shows.TvShowFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun provideRepository(): CatalogMovieDataSource

    fun inject(tvShowFragment: TvShowFragment)
    fun inject(movieFragment: MovieFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(detailActivity: DetailActivity)
}