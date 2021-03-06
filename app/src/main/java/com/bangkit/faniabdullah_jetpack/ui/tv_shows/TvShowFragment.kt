package com.bangkit.faniabdullah_jetpack.ui.tv_shows

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
import com.bangkit.faniabdullah_jetpack.databinding.FragmentTvShowsBinding
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bangkit.faniabdullah_jetpack.vo.Status
import javax.inject.Inject

class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val tvShowViewModel: TvShowViewModel by viewModels {
        factory
    }

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding as FragmentTvShowsBinding
    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvShowsAdapter = TvShowsAdapter()

        binding.rvTvShows.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
        tvShowViewModel.getTvShowsPopular().observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING ->
                        showLoading(true)
                    Status.SUCCESS -> {
                        movie.data?.let {
                            tvShowsAdapter.submitList(it)
                            showLoading(false)
                            showEmptyLayout(false)
                        }
                    }
                    Status.ERROR -> {
                        showEmptyLayout(true)
                    }
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {

        if (state) {
            binding.rvTvShows.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.rvTvShows.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showEmptyLayout(state: Boolean) {
        if (state) {
            binding.rvTvShows.visibility = View.VISIBLE
            binding.notifyLayout.apply {
                messageNotify.visibility = View.GONE
                pictureNotify.visibility = View.GONE
                messageNotify.text = getString(R.string.notification_error_server)
            }
            binding.notifyLayout
            binding.notifyLayout
        } else {
            binding.rvTvShows.visibility = View.VISIBLE
            binding.notifyLayout.apply {
                messageNotify.visibility = View.GONE
                pictureNotify.visibility = View.GONE
            }
        }

    }
}