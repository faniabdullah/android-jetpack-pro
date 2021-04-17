package com.bangkit.faniabdullah_jetpack.ui.detailmovie

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.ActivityDetailBinding
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra(Constant.MOVIE_DETAIL) as MovieEntity?

        movie?.apply {
            binding.apply {
                title = "Detail"
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
}