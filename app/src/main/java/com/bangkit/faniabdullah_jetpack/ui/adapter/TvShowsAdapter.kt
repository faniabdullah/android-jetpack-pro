package com.bangkit.faniabdullah_jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.MovieItemBinding
import com.bangkit.faniabdullah_jetpack.model.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.MovieViewHolder>() {
    private val list = ArrayList<TvShowsEntity>()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MovieItemBinding.bind(itemView)

        fun bind(movie: TvShowsEntity) {
            binding.apply {
                tvTitle.text = movie.original_name
                tvOverview.text = movie.overview
                Glide.with(itemView)
                    .load("${Constant.BASE_IMAGE_URL}${movie.poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(imageView)
            }
        }
    }

    fun setList(tv_item: ArrayList<TvShowsEntity>) {
        list.clear()
        list.addAll(tv_item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}