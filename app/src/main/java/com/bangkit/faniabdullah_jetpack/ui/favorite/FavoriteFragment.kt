package com.bangkit.faniabdullah_jetpack.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
                movieAdapter.notifyDataSetChanged()
            })

            favoriteViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { movie ->

                tvShowsAdapter.submitList(movie)
                movieAdapter.notifyDataSetChanged()
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
}