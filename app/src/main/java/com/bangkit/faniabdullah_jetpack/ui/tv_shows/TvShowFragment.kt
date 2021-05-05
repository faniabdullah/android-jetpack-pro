package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.R
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity
import com.bangkit.faniabdullah_jetpack.databinding.FragmentTvShowsBinding
import com.bangkit.faniabdullah_jetpack.ui.adapter.TvShowsAdapter
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant
import com.bangkit.faniabdullah_jetpack.utils.ViewModelFactory
import com.bangkit.faniabdullah_jetpack.utils.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding as FragmentTvShowsBinding
    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = TvShowsAdapter()

        showLoading(true)


        binding.apply {
            rvTvShows.layoutManager = GridLayoutManager(activity, 2)
            rvTvShows.setHasFixedSize(true)
            rvTvShows.adapter = adapter
        }

        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]


        tvShowViewModel.getTvShowsPopular().observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movie.data?.let { adapter.setList(it)
                        }
                        adapter.notifyDataSetChanged()
                        Log.e("see",""+adapter.itemCount)
                    }
                    Status.ERROR ->{

                    }
                }
            }

            showLoading(false)

            showEmptyLayout(false)
        })



        adapter.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback {

            override fun onItemClicked(data: TvShowsEntity) {
                showDetailMovie(data)
            }
        })

    }

    private fun showDetailMovie(data: TvShowsEntity) {
        val intentDetail = Intent(context, DetailActivity::class.java)
        intentDetail.putExtra(Constant.MOVIE_ID, data.tvShows_id)
            .putExtra(Constant.KEY_TYPE, Constant.TV_SHOWS_TYPE)
        startActivity(intentDetail)
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
            binding.notifyLayout.messageNotify.visibility = View.GONE
            binding.notifyLayout.pictureNotify.visibility = View.GONE
            binding.notifyLayout.messageNotify.text = getString(R.string.notification_error_server)
        } else {
            binding.rvTvShows.visibility = View.VISIBLE
            binding.notifyLayout.messageNotify.visibility = View.GONE
            binding.notifyLayout.pictureNotify.visibility = View.GONE
        }

    }
}