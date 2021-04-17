package com.bangkit.faniabdullah_jetpack.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.faniabdullah_jetpack.databinding.FragmentMovieBinding
import com.bangkit.faniabdullah_jetpack.model.MovieEntity
import com.bangkit.faniabdullah_jetpack.ui.adapter.MovieAdapter
import com.bangkit.faniabdullah_jetpack.ui.detailmovie.DetailActivity
import com.bangkit.faniabdullah_jetpack.utils.Constant

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
        adapter.notifyDataSetChanged()

        binding.apply {
            rvMovie.layoutManager = GridLayoutManager(activity, 2)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
        }
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val moviePlaying = movieViewModel.getMovieNowPlayingNewsVersion()
        adapter.setList(moviePlaying)


        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieEntity) {
                showDetailMovie(data)
            }
        })

    }

    private fun showDetailMovie(data: MovieEntity) {
        val intentDetail = Intent(context, DetailActivity::class.java)
        intentDetail.putExtra(Constant.MOVIE_DETAIL, data)
        startActivity(intentDetail)
    }
}