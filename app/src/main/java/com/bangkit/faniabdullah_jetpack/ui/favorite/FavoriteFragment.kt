package com.bangkit.faniabdullah_jetpack.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.databinding.FragmentFavoriteBinding
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private lateinit var movieAdapter: MovieFavoriteAdapter
    private lateinit var tvShowsAdapter: TvShowsFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieFavoriteAdapter()
        tvShowsAdapter = TvShowsFavoriteAdapter()

        binding.favoriteMovie.apply {
            rvMovie.layoutManager =
                LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = movieAdapter
        }

        binding.favoriteTvshows.apply {
            rvTvShows.layoutManager =
                LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
            rvTvShows.setHasFixedSize(true)
            rvTvShows.adapter = tvShowsAdapter
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]


        favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { movie ->
            movieAdapter.submitList(movie)
        })

        favoriteViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { movie ->
            tvShowsAdapter.submitList(movie)
        })
    }

    private fun showDetailMovie(movie: MovieEntity?, tvShows: TvShowsEntity?) {
        val intentDetail = Intent(activity, DetailActivity::class.java)

        if (movie != null) {
            intentDetail.putExtra(Constant.MOVIE_ID, movie.movie_id)
                .putExtra(Constant.KEY_TYPE, Constant.MOVIE_TYPE)
        } else {
            if (tvShows != null) {
                intentDetail.putExtra(Constant.MOVIE_ID, tvShows.tvShows_id)
                    .putExtra(Constant.KEY_TYPE, Constant.TV_SHOWS_TYPE)
            }
        }

        startActivity(intentDetail)
    }
}