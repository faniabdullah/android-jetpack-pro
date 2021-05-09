package com.bangkit.faniabdullah_jetpack.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.faniabdullah_jetpack.MyApplication
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.FragmentFavoriteBinding
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private lateinit var movieAdapter: MovieFavoriteAdapter
    private lateinit var tvShowsAdapter: TvShowsFavoriteAdapter


    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieFavoriteAdapter()
        tvShowsAdapter = TvShowsFavoriteAdapter()


        if (activity != null) {
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
                rvMovieFavorite.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                rvMovieFavorite.setHasFixedSize(true)
                rvMovieFavorite.adapter = movieAdapter
            }

            binding.favoriteTvshows.apply {
                rvTvShowsFavorite.layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                rvTvShowsFavorite.setHasFixedSize(true)
                rvTvShowsFavorite.adapter = tvShowsAdapter
            }

        }

    }

    private fun isEmptyMovieFavorites(state: Boolean) {
        if (state) {
            binding.favoriteMovie.apply {
                notifyMovieLayout.apply {
                    messageNotifyMovie.text = getString(R.string.notification_empty_favorite)
                    messageNotifyMovie.visibility = View.VISIBLE
                    pictureNotifyMovie.visibility = View.VISIBLE
                }
                rvMovieFavorite.visibility = View.GONE
            }
        } else {
            binding.favoriteMovie.apply {
                notifyMovieLayout.apply {
                    messageNotifyMovie.visibility = View.GONE
                    pictureNotifyMovie.visibility = View.GONE
                }
                rvMovieFavorite.visibility = View.VISIBLE
            }
        }
    }

    private fun isEmptyTvShowsFavorites(state: Boolean) {
        if (state) {
            binding.favoriteTvshows.apply {
                notifyLayoutTvShows.apply {
                    messageNotifyTv.text = getString(R.string.notification_empty_favorite)
                    messageNotifyTv.visibility = View.VISIBLE
                    pictureNotifyTv.visibility = View.VISIBLE
                }
                rvTvShowsFavorite.visibility = View.GONE
            }
        } else {
            binding.favoriteTvshows.apply {
                notifyLayoutTvShows.apply {
                    messageNotifyTv.visibility = View.GONE
                    pictureNotifyTv.visibility = View.GONE
                }
                rvTvShowsFavorite.visibility = View.VISIBLE
            }
        }
    }
}