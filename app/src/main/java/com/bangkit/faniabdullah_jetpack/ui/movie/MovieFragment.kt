package com.bangkit.faniabdullah_jetpack.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.databinding.FragmentMovieBinding
import com.bangkit.faniabdullah_jetpack.ui.adapter.MovieAdapter
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bangkit.faniabdullah_jetpack.utils.vo.Status

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter()

        showLoading(true)

        binding.apply {
            rvMovie.layoutManager = GridLayoutManager(activity, 2)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]


        movieViewModel.getMovieNowPlaying().observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movie.data?.let { adapter.setList(it) }
                        adapter.notifyDataSetChanged()
                        showEmptyLayout(false)
                    }
                    else -> showLoading(false)
                }
            }

            showLoading(false)
        })

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {

            override fun onItemClicked(data: MovieEntity) {
                showDetailMovie(data)
            }
        })
    }

    private fun showEmptyLayout(state: Boolean) {
        if (state) {
            binding.rvMovie.visibility = View.GONE
            binding.notifyLayout.messageNotify.visibility = View.VISIBLE
            binding.notifyLayout.pictureNotify.visibility = View.VISIBLE
            binding.notifyLayout.messageNotify.text = getString(R.string.notification_error_server)
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.notifyLayout.messageNotify.visibility = View.GONE
            binding.notifyLayout.pictureNotify.visibility = View.GONE
        }

    }

    private fun showDetailMovie(data: MovieEntity) {
        val intentDetail = Intent(activity, DetailActivity::class.java)
        intentDetail.putExtra(Constant.MOVIE_ID, data.movie_id)
            .putExtra(Constant.KEY_TYPE, Constant.MOVIE_TYPE)
        startActivity(intentDetail)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvMovie.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}