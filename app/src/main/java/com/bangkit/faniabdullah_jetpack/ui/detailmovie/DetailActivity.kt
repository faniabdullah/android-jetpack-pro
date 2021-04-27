package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.ActivityDetailBinding
import com.bangkit.faniabdullah_jetpack.domain.model.DetailMovieData
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
        val movieDa = intent?.getStringExtra(Constant.MOVIE_ID)
        val movie = movieDa?.toInt()
        val typeMovie = intent.getStringExtra(Constant.KEY_TYPE)
        val factory = ViewModelFactory.getInstance()
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (movie != null) {
            if (typeMovie == Constant.MOVIE_TYPE) {
                detailViewModel.getDetailMovieById(movie).observe(this, {
                    displayData(it)

                    showLoading(false)
                })
            } else {
                detailViewModel.getDetailTvShowById(movie).observe(this, {
                    displayData(it)
                    showLoading(false)
                })
            }
        }
    }

    private fun displayData(data: DetailMovieData?) {
        data?.apply {
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
            binding.contentDetail.progressBar.visibility = View.VISIBLE
        } else {
            binding.contentDetail.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}