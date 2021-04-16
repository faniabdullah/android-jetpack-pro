package com.bangkit.faniabdullah_jetpack.ui.tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.faniabdullah_jetpack.R

class TvShowFragment : Fragment() {

    private lateinit var homeViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }
}