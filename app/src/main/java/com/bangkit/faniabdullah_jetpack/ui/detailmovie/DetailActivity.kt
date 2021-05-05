package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.databinding.ActivityDetailBinding
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showLoading(true)

        val movie = intent?.getIntExtra(Constant.MOVIE_ID, 1)


        val typeMovie = intent.getStringExtra(Constant.KEY_TYPE)
        val factory = ViewModelFactory.getInstance(this)

        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (movie != null) {
            if (typeMovie == Constant.MOVIE_TYPE) {
                detailViewModel.getDetailMovieById(movie).observe(this, {
                    displayDataMovie(it)
                    showLoading(false)
                })
            } else {
                detailViewModel.getDetailTvShowById(movie).observe(this, {
                    displayDataTvShows(it)
                    showLoading(false)
                })
            }
        }

    }

    private fun displayDataMovie(data: MovieEntity) {
        data.apply {
            binding.apply {
                contentDetail.tvInfoMovie.text = vote_average.toString()
                contentDetail.tvMovieDetailTitle.text = original_title
                contentDetail.tvOverviewDetail.text = overview
                Glide.with(this@DetailActivity)
                    .load("${Constant.BASE_IMAGE_URL}${poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(contentDetail.posterMovie)
            }
        }
    }

    private fun displayDataTvShows(data: TvShowsEntity) {
        data.apply {
            binding.apply {
                contentDetail.tvInfoMovie.text = vote_average.toString()
                contentDetail.tvMovieDetailTitle.text = original_title
                contentDetail.tvOverviewDetail.text = overview
                Glide.with(this@DetailActivity)
                    .load("${Constant.BASE_IMAGE_URL}${poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(contentDetail.posterMovie)
            }
        }
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