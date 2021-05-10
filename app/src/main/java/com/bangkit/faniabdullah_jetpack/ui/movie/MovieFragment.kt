package com.bangkit.faniabdullah_jetpack.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.MyApplication
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.databinding.FragmentMovieBinding
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bangkit.faniabdullah_jetpack.vo.Status
import javax.inject.Inject

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private val movieViewModel: MovieViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()

        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieViewModel.getMovieNowPlaying().observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        movie.data?.let {
                            movieAdapter.submitList(it)
                            showLoading(false)
                        }
                        showEmptyLayout(false)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showEmptyLayout(true)
                    }
                }
            }
        })


    }

    private fun showEmptyLayout(state: Boolean) {
        if (state) {
            binding.rvMovie.visibility = View.GONE
            binding.notifyLayout.apply {
                messageNotify.visibility = View.VISIBLE
                pictureNotify.visibility = View.VISIBLE
                messageNotify.text = getString(R.string.notification_error_server)
            }
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.notifyLayout.apply {
                messageNotify.visibility = View.GONE
                pictureNotify.visibility = View.GONE
            }

        }

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