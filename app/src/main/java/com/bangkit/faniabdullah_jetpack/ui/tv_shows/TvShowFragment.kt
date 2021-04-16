package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.databinding.FragmentTvShowsBinding
import com.bangkit.faniabdullah_jetpack.ui.adapter.MovieAdapter
import com.bangkit.faniabdullah_jetpack.ui.adapter.TvShowsAdapter
import com.bangkit.faniabdullah_jetpack.ui.movie.MovieViewModel

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
        super.onViewCreated(view, savedInstanceState)
        adapter = TvShowsAdapter()
        adapter.notifyDataSetChanged()


        binding.apply {
            rvTvShows.layoutManager = GridLayoutManager(activity, 2)
            rvTvShows.setHasFixedSize(true)
            rvTvShows.adapter = adapter
        }

        tvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)

        tvShowViewModel.checkStatusServer().observe(viewLifecycleOwner, {

        })

        tvShowViewModel.setPopularTvShows()

        tvShowViewModel.getTvShowsPopular().observe(viewLifecycleOwner, {
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }

        })
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}