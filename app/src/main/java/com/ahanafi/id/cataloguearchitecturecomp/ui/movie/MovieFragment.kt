package com.ahanafi.id.cataloguearchitecturecomp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahanafi.id.cataloguearchitecturecomp.R
import com.ahanafi.id.cataloguearchitecturecomp.adapter.MyPagedListAdapter
import com.ahanafi.id.cataloguearchitecturecomp.data.DataViewModel
import com.ahanafi.id.cataloguearchitecturecomp.databinding.FragmentMovieBinding
import com.ahanafi.id.cataloguearchitecturecomp.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        binding.lifecycleOwner = this@MovieFragment

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory).get(DataViewModel::class.java)
        val dataAdapter = MyPagedListAdapter()

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { data ->
            dataAdapter.submitList(data)
            dataAdapter.notifyDataSetChanged()
        })

        binding.rvMovie.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
    }

    /*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            if (progressbar != null) {
                progressbar.visibility = View.VISIBLE
            }
            viewModel.getMovies().observe(requireActivity(), Observer { movie ->
                if (progressbar != null) {
                    progressbar.visibility = View.GONE
                }

            })

            with(rv_movie) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }
    }
    */
}
