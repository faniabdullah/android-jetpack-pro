package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.databinding.MovieItemBinding
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class TvShowsAdapter :
    PagedListAdapter<TvShowsEntity, TvShowsAdapter.TvShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.tvShows_id == newItem.tvShows_id
            }

            override fun areContentsTheSame(
                oldItem: TvShowsEntity,
                newItem: TvShowsEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TvShowsViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShowsEntity) {
            binding.apply {
                tvTitle.text = movie.original_title
                tvOverview.text = movie.overview
                Glide.with(itemView)
                    .load("${Constant.BASE_IMAGE_URL}${movie.poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(posterMovie)
            }

            itemView.setOnClickListener {
                val intentDetail = Intent(itemView.context, DetailActivity::class.java)
                intentDetail.putExtra(Constant.MOVIE_ID, movie.tvShows_id)
                    .putExtra(Constant.KEY_TYPE, Constant.TV_SHOWS_TYPE)
                itemView.context.startActivity(intentDetail)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val mView = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(mView)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}