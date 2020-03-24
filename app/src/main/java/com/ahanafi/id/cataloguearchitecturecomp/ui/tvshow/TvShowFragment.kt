package com.ahanafi.id.cataloguearchitecturecomp.ui.tvshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.progressbar
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private lateinit var viewModel : TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null) {
            progressbar.visibility = View.VISIBLE
            val factory = ViewModelFactory.getInstance(requireActivity())
            val tvShowAdapter = TvShowAdapter()

            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            viewModel.getTvShows().observe(requireActivity(), Observer { tvShow ->
                tvShowAdapter.setTvShow(tvShow)
                progressbar.visibility = View.INVISIBLE
                tvShowAdapter.notifyDataSetChanged()
            })

            with(rv_tv_show) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }


}
