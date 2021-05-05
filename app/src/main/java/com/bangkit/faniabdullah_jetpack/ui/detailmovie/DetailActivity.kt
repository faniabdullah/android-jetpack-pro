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
            binding.contentDetail.tvOverviewDetail.visibility = View.GONE
            binding.contentDetail.tvTitleOverview.visibility = View.GONE
            binding.contentDetail.tvMovieDetailTitle.visibility = View.GONE
            binding.contentDetail.tvInfoMovie.visibility = View.GONE
        } else {
            binding.contentDetail.progressBar.visibility = View.GONE
            binding.contentDetail.tvOverviewDetail.visibility = View.VISIBLE
            binding.contentDetail.tvTitleOverview.visibility = View.VISIBLE
            binding.contentDetail.tvMovieDetailTitle.visibility = View.VISIBLE
            binding.contentDetail.tvInfoMovie.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}