package com.bangkit.faniabdullah_jetpack.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.MovieItemBinding
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val list = ArrayList<MovieEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MovieItemBinding.bind(itemView)

        fun bind(movie: MovieEntity) {
            binding.apply {
                tvTitle.text = movie.original_title
                tvOverview.text = movie.overview
                Glide.with(itemView)
                    .load("${Constant.BASE_IMAGE_URL}${movie.poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.placeholder_movie)
                    .into(posterMovie)
            }
            itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
        }
    }

    fun setList(movies: List<MovieEntity>) {
        list.clear()
        list.addAll(movies)
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