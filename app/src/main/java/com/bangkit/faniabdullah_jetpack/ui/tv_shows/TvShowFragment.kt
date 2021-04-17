package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.databinding.FragmentTvShowsBinding
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.ui.adapter.MovieAdapter
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding as FragmentTvShowsBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()


        binding.apply {
            rvTvShows.layoutManager = GridLayoutManager(activity, 2)
            rvTvShows.setHasFixedSize(true)
            rvTvShows.adapter = adapter
        }

        tvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        val tvShowsPopular = tvShowViewModel.getTvShowsPopularNewsVersion()
        adapter.setList(tvShowsPopular)

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                showDetailMovie(data)
            }
        })
    }

    private fun showDetailMovie(data: MovieEntity) {
        val intentDetail = Intent(context, DetailActivity::class.java)
        intentDetail.putExtra(Constant.MOVIE_ID, data.id)
            .putExtra(Constant.KEY_TYPE, Constant.TV_SHOWS_TYPE)
        startActivity(intentDetail)
    }
}