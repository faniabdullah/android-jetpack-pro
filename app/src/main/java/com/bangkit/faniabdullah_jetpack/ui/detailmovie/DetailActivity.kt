package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.faniabdullah_jetpack.MyApplication
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.databinding.ActivityDetailBinding
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailViewModel: DetailViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(true)

        val movie = intent?.getIntExtra(Constant.MOVIE_ID, 1)
        val typeMovie = intent.getStringExtra(Constant.KEY_TYPE)
//        val factory = ViewModelFactory.getInstance(this)
//
//        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (movie != null) {
            if (typeMovie == Constant.MOVIE_TYPE) {
                detailViewModel.getDetailMovieById(movie).observe(this, {
                    displayDataMovie(it)
                    setActionButton(it, null)
                    showLoading(false)
                })
            } else {
                detailViewModel.getDetailTvShowById(movie).observe(this, {
                    displayDataTvShows(it)
                    setActionButton(null, it)
                    showLoading(false)
                })
            }
        }
    }

    private fun setActionButton(movie: MovieEntity?, tvShow: TvShowsEntity?) {
        binding.contentDetail.addToFavorite.setOnClickListener {
            setBookmark(movie, tvShow)
        }
    }

    private fun displayDataMovie(data: MovieEntity) {
        data.apply {
            binding.contentDetail.apply {
                tvInfoMovie.text = vote_average.toString()
                tvMovieDetailTitle.text = original_title
                tvOverviewDetail.text = overview
                Glide.with(this@DetailActivity)
                    .load("${Constant.BASE_IMAGE_URL}${poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(posterMovie)
            }
            setBookmarkedState(favorite)
        }
    }

    private fun displayDataTvShows(data: TvShowsEntity) {
        data.apply {
            binding.contentDetail.apply {
                tvInfoMovie.text = vote_average.toString()
                tvMovieDetailTitle.text = original_title
                tvOverviewDetail.text = overview
                Glide.with(this@DetailActivity)
                    .load("${Constant.BASE_IMAGE_URL}${poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(posterMovie)
            }
            setBookmarkedState(favorite)
        }
    }

    private fun setBookmark(movie: MovieEntity?, tvShow: TvShowsEntity?) {
        if (movie != null) {
            if (movie.favorite) {
                showSnackBar("${movie.original_title} " + getString(R.string.removed_from_favorite))
            } else {
                showSnackBar("${movie.original_title} " + getString(R.string.add_to_favorite))
            }
            detailViewModel.setBookmarkedMovies(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.favorite) {
                    showSnackBar("${tvShow.original_title} " + getString(R.string.removed_from_favorite))
                } else {
                    showSnackBar("${tvShow.original_title} " + getString(R.string.add_to_favorite))
                }
                detailViewModel.setBookmarkedTvShow(tvShow)
            }
        }
    }

    private fun setBookmarkedState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.contentDetail.addToFavorite.setImageResource(R.drawable.ic_baseline_favorite_blue_24)
        } else {
            binding.contentDetail.addToFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_blue_24)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.contentDetail.apply {
                progressBar.visibility = View.VISIBLE
                tvOverviewDetail.visibility = View.GONE
                tvTitleOverview.visibility = View.GONE
                tvMovieDetailTitle.visibility = View.GONE
                tvInfoMovie.visibility = View.GONE
            }

        } else {
            binding.contentDetail.apply {
                progressBar.visibility = View.GONE
                tvOverviewDetail.visibility = View.VISIBLE
                tvTitleOverview.visibility = View.VISIBLE
                tvMovieDetailTitle.visibility = View.VISIBLE
                tvInfoMovie.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}