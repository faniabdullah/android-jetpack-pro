package com.bangkit.faniabdullah_jetpack.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.FragmentFavoriteBinding
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


        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]


            favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { movie ->
                movieAdapter.submitList(movie)
                if (movie.size > 0) {
                    isEmptyMovieFavorites(false)
                } else {
                    isEmptyMovieFavorites(true)
                }
            })

            favoriteViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { movie ->
                tvShowsAdapter.submitList(movie)
                if (movie.size > 0) {
                    isEmptyTvShowsFavorites(false)
                } else {
                    isEmptyTvShowsFavorites(true)
                }
            })

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

        }

    }

    private fun isEmptyMovieFavorites(state: Boolean) {
        if (state) {
            binding.favoriteMovie.apply {
                notifyMovieLayout.apply {
                    messageNotify.text = getString(R.string.notification_empty_favorite)
                    messageNotify.visibility = View.VISIBLE
                    pictureNotify.visibility = View.VISIBLE
                }
                rvMovie.visibility = View.GONE
            }
        } else {
            binding.favoriteMovie.apply {
                notifyMovieLayout.apply {
                    messageNotify.visibility = View.GONE
                    pictureNotify.visibility = View.GONE
                }
                rvMovie.visibility = View.VISIBLE
            }
        }
    }

    private fun isEmptyTvShowsFavorites(state: Boolean) {
        if (state) {
            binding.favoriteTvshows.apply {
                notifyLayoutTvShows.apply {
                    messageNotify.text = getString(R.string.notification_empty_favorite)
                    messageNotify.visibility = View.VISIBLE
                    pictureNotify.visibility = View.VISIBLE
                }
                rvTvShows.visibility = View.GONE
            }
        } else {
            binding.favoriteTvshows.apply {
                notifyLayoutTvShows.apply {
                    messageNotify.visibility = View.GONE
                    pictureNotify.visibility = View.GONE
                }
                rvTvShows.visibility = View.VISIBLE
            }
        }
    }
}